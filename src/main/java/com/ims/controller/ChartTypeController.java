package com.ims.controller;

import com.ims.model.ChartType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sdrahnea
 */
@RestController
@RequestMapping(value = "/chart-type")
public class ChartTypeController extends AbstractController<ChartType> {
}