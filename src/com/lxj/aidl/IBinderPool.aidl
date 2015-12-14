package com.lxj.aidl;

interface IBinderPool {
    IBinder queryBinder(in int binderCode);
}