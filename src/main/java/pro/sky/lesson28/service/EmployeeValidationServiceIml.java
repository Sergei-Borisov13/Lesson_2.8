package pro.sky.lesson28.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.lesson28.exception.InvalidEmployeeDataException;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeValidationServiceIml implements EmployeeValidationService{
    @Override
    public void validate(String firstName, String lastName) {
        validateName(firstName);
        validateName(lastName);

    }

    private void validateName(String name) {
        if (!isAlpha(name)) {
            throw new InvalidEmployeeDataException("Некорректное значение" + name);
        }
    }
}
