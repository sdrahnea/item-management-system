package com.ims.controller;

import com.ims.model.Constant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sdrahnea
 */
@RestController
@RequestMapping(value = "/constant")
public class ConstantController extends AbstractController<Constant> {
}