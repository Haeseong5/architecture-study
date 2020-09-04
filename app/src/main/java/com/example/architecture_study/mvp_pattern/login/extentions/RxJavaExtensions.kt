package com.example.architecture_study.mvp_pattern.login.extentions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable){
    this.add(disposable)
}