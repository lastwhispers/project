package cn.lastwhisper.project.validator;

import cn.lastwhisper.project.validator.annotation.CheckPhone;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static cn.lastwhisper.project.enums.ResultEnum.*;

/**
 *
 * @author lastwhisper
 * @date 2020/5/28
 */
public class PhoneValidator implements ConstraintValidator<CheckPhone, String> {

    /**
     * 138、166、198、199
     */
    private Pattern phonePattern = Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
    /**
     * 0-9
     */
    private Pattern illegalPattern = Pattern.compile("[^0-9]");

    @Override
    public void initialize(CheckPhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 空串非法
        if (StringUtils.isEmpty(value)) {
            throw new ConstraintDeclarationException(PHONE_NULL.getMsg());
        }
        value = value.length() == 13 ? value.replace(" ", "") : value;
        // 长度非法
        if (value.length() != 11) {
            throw new ConstraintDeclarationException(PHONE_LENGTH_ILLEGAL.getMsg());
        }
        // 包含字母
        if (illegalPattern.matcher(value).find()) {
            throw new ConstraintDeclarationException(PHONE_ILLEGAL.getMsg());
        }
        // 不属于中国大陆
        if (!phonePattern.matcher(value).matches()) {
            throw new ConstraintDeclarationException(PHONE_NOT_CHINA_ILLEGAL.getMsg());
        }

        return true;
    }
}