package com.rui.cn.service;

import com.rui.cn.entity.Animal;

import java.util.concurrent.Future;


public interface ICollapsingService {
    public Future<Animal> collapsing(Integer id);

    public Animal collapsingSyn(Integer id);

    public Future<Animal> collapsingGlobal(Integer id);

}