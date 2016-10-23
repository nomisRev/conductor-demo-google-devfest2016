package be.vergauwen.simon.conductor.ui.controllers;


import android.view.View;

import com.bluelinelabs.conductor.Controller;

public interface LayoutBinder<T extends Controller> {
    View bind(T t);
    View unbind(T t);
}
