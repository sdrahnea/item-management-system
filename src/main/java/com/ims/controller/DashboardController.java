package com.ims.controller;

import com.ims.model.Dashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sdrahnea
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController extends AbstractController<Dashboard> {

}