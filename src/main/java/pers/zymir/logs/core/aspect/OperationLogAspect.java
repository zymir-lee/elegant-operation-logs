package pers.zymir.logs.core.aspect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import pers.zymir.logs.core.anno.EnableOperationLog;
import pers.zymir.logs.core.anno.OperationLog;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class OperationLogAspect {

    // TODO @AfterReturning持久化日志

    ExpressionParser expressionParser = new SpelExpressionParser();
    ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Before("@within(pers.zymir.logs.core.anno.EnableOperationLog)")
    public void recordOperationLog(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method apiMethod = methodSignature.getMethod();

        Class<?> controllerClass = joinPoint.getTarget().getClass();
        EnableOperationLog enableOperationLog = controllerClass.getAnnotation(EnableOperationLog.class);
        OperationLog operationLog = apiMethod.getAnnotation(OperationLog.class);
        if (Objects.nonNull(enableOperationLog) && Objects.nonNull(enableOperationLog.proxyClass())) {
            proxy(enableOperationLog.proxyClass(), apiMethod.getName(), joinPoint.getArgs());
            return;
        }
        common(operationLog, apiMethod, joinPoint.getArgs());
    }

    private void common(OperationLog operationLog, Method apiMethod, Object... args) {
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(apiMethod);
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        assert parameterNames != null;
        for (int i = 0; i < parameterNames.length; i++) {
            evaluationContext.setVariable(parameterNames[i], args[i]);
        }
        Expression expression = expressionParser.parseExpression(operationLog.detail());
        Object value = expression.getValue(evaluationContext);

        System.out.println(value);
    }

    @SneakyThrows
    public void proxy(Class<?> proxyClass, String methodName, Object... params) {
        if (proxyClass == void.class) {
            log.warn("未实现日志代理类");
            return;
        }

        Object logProxyObject = SpringUtil.getBean(proxyClass);
        if (Objects.isNull(logProxyObject)) {
            log.warn("未实现日志代理类");
            return;
        }

        Class<?>[] paramTypes = getType(params);
        Method logProxyMethod = ReflectUtil.getMethod(proxyClass, methodName, paramTypes);
        if (Objects.isNull(logProxyMethod)) {
            log.warn("实现日志代理类未实现该方法. ProxyClass: {}, Method: {}", proxyClass.getSimpleName(), methodName);
            return;
        }

        Object invoke = logProxyMethod.invoke(logProxyObject, params);
    }


    public Class<?>[] getType(Object... params) {
        Class<?>[] classes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            classes[i] = params[i].getClass();
        }
        return classes;
    }
}
