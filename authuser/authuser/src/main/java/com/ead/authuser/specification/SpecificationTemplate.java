package com.ead.authuser.specification;

import com.ead.authuser.model.UserModel;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    public interface UserSpec extends Specification<UserModel>{}
}
