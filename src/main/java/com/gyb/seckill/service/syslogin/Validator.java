package com.gyb.seckill.service.syslogin;

/**
 * @author geng
 * 2020/4/19
 */
public interface Validator {
    void validate(ValidatorInput input,ValidatorOutput output,ValidatorChain chain);
}
