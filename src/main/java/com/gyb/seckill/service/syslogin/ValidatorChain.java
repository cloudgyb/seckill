package com.gyb.seckill.service.syslogin;

/**
 * @author geng
 * 2020/4/19
 */
public interface ValidatorChain {
    int getValidatorSize();
    void doValidate(ValidatorInput input,ValidatorOutput output);
}
