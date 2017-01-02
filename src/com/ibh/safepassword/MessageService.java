/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author ihorvath
 */
public class MessageService {
  private static Map<Type, Runnable> _messages = new HashMap<>();
  
//    public static void Register(Type t, Supplier<Void> callback) {

  public static void Register(Type t, Runnable callback) {
    _messages.put(t, callback);
  }
  public static void UnRegister(IMessage mess, Method func) {
    
  }
  public static void Send(Type t) {
    Runnable callback = _messages.get(t);
    callback.run();
  }
}
