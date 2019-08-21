/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nodeweave.test.basiccalculatorrest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author misaf
 */
@RestController
@RequestMapping("/calc")
public class BasicCalculator {

    private static final double DIGIT_LIMIT = 999999999;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/sqrt/{current}", produces = "text/plain")
    public String squareRoot(@PathVariable("current") double current) {
        double tmpValue;
//        if (current < 0) {
//            current *= -1;
//            tmpValue = roundNumber(Math.sqrt(current));
//            return tmpValue > DIGIT_LIMIT ? "E" : String.valueOf(tmpValue) + "i";
//        }

        tmpValue = roundNumber(Math.sqrt(current));
        return tmpValue > DIGIT_LIMIT ? "E" : String.valueOf(tmpValue);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/div/{previous}/{current}", produces = "text/plain")
    public String divide(@PathVariable("previous") double previous, @PathVariable("current") double current) {
        double tmpValue = roundNumber(previous / current);
        return tmpValue > DIGIT_LIMIT ? "E" : String.valueOf(tmpValue);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/mult/{previous}/{current}", produces = "text/plain")
    public String multiply(@PathVariable("previous") double previous, @PathVariable("current") double current) {
        double tmpValue = roundNumber(previous * current);
        return tmpValue > DIGIT_LIMIT ? "E" : String.valueOf(tmpValue);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/sub/{previous}/{current}", produces = "text/plain")
    public String subtract(@PathVariable("previous") double previous, @PathVariable("current") double current) {
        double tmpValue = roundNumber(previous - current);
        return tmpValue > DIGIT_LIMIT ? "E" : String.valueOf(tmpValue);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/add/{previous}/{current}", produces = "text/plain")
    public String add(@PathVariable("previous") double previous, @PathVariable("current") double current) {
        double tmpValue = roundNumber(previous + current);
        return tmpValue > DIGIT_LIMIT ? "E" : String.valueOf(tmpValue);
    }

    private double roundNumber(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

}
