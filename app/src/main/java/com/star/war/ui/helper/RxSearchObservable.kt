package com.star.war.ui.helper

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Search Observable used for handling call back for SearchView.
 */
class RxSearchObservable {
    companion object {
        fun fromView(searchView: SearchView): Observable<String> {
            val subject = PublishSubject.create<String>()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String?): Boolean {
                    subject.onComplete()
                    return true
                }
                override fun onQueryTextChange(text: String): Boolean {
                    subject.onNext(text)
                    return true
                }
            })
            return subject.hide()
        }
    }
}