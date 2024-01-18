package pers.zymir.logs.core.entity;

import lombok.Data;

@Data
public class OperationLogEntity {
  private String module;
  private String subModule;
  private String content;
  private Long timestamp = System.currentTimeMillis();
  private Long operatorUserId;
  private String operator;
}
