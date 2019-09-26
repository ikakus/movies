package me.scraplesh.module.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class MviCoreView<UiEvent, State> private constructor(
  protected val uiEvents: Relay<UiEvent>,
  protected val states: Relay<State>,
  protected val disposeBag: CompositeDisposable
) : Consumer<State> by states,
  ObservableSource<UiEvent> by uiEvents,
  Disposable by disposeBag,
  DefaultLifecycleObserver {

  constructor() : this(
    uiEvents = PublishRelay.create(),
    states = BehaviorRelay.create(),
    disposeBag = CompositeDisposable()
  )

  abstract fun getView(inflater: LayoutInflater, container: ViewGroup?): View

  override fun onDestroy(owner: LifecycleOwner) {
    disposeBag.clear()
  }
}
