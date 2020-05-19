package com.gyb.seckill.service.syslogin;

/**
 * @author geng
 * 2020/4/19
 */
public class LoginValidatorChain implements ValidatorChain {
    private Validator[] validators;
    private int total;
    private int index;

    public LoginValidatorChain(Validator[] validators){
        assert validators!=null;
        this.validators = validators;
        this.total = validators.length;
        this.index = 0;
    }
    public LoginValidatorChain(){
        this.validators = new Validator[0];
        this.total = 0;
        this.index = 0;
    }

    @Override
    public void doValidate(ValidatorInput input, ValidatorOutput output) {
        if(index < total){
            Validator validator = validators[index++];
            validator.validate(input,output,this);
        }
    }

    public LoginValidatorChain addValidator(Validator validator){
        Validator[] newValidators = new Validator[this.validators.length + 1];
        System.arraycopy(this.validators,0,newValidators,0,this.validators.length);
        newValidators[this.validators.length] = validator;
        this.validators = newValidators;
        this.total = this.validators.length;
        return this;
    }

    @Override
    public int getValidatorSize() {
        return this.total;
    }
}
