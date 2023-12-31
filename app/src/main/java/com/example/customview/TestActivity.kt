package com.example.customview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class TestActivity : AppCompatActivity() {

    inner class AmsInvocationHandler(val iActivityManagerObject: Any?): InvocationHandler{
        /**
         * Processes a method invocation on a proxy instance and returns
         * the result.  This method will be invoked on an invocation handler
         * when a method is invoked on a proxy instance that it is
         * associated with.
         *
         * @param   proxy the proxy instance that the method was invoked on
         *
         * @param   method the `Method` instance corresponding to
         * the interface method invoked on the proxy instance.  The declaring
         * class of the `Method` object will be the interface that
         * the method was declared in, which may be a superinterface of the
         * proxy interface that the proxy class inherits the method through.
         *
         * @param   args an array of objects containing the values of the
         * arguments passed in the method invocation on the proxy instance,
         * or `null` if interface method takes no arguments.
         * Arguments of primitive types are wrapped in instances of the
         * appropriate primitive wrapper class, such as
         * `java.lang.Integer` or `java.lang.Boolean`.
         *
         * @return  the value to return from the method invocation on the
         * proxy instance.  If the declared return type of the interface
         * method is a primitive type, then the value returned by
         * this method must be an instance of the corresponding primitive
         * wrapper class; otherwise, it must be a type assignable to the
         * declared return type.  If the value returned by this method is
         * `null` and the interface method's return type is
         * primitive, then a `NullPointerException` will be
         * thrown by the method invocation on the proxy instance.  If the
         * value returned by this method is otherwise not compatible with
         * the interface method's declared return type as described above,
         * a `ClassCastException` will be thrown by the method
         * invocation on the proxy instance.
         *
         * @throws  Throwable the exception to throw from the method
         * invocation on the proxy instance.  The exception's type must be
         * assignable either to any of the exception types declared in the
         * `throws` clause of the interface method or to the
         * unchecked exception types `java.lang.RuntimeException`
         * or `java.lang.Error`.  If a checked exception is
         * thrown by this method that is not assignable to any of the
         * exception types declared in the `throws` clause of
         * the interface method, then an
         * [UndeclaredThrowableException] containing the
         * exception that was thrown by this method will be thrown by the
         * method invocation on the proxy instance.
         *
         * @see UndeclaredThrowableException
         */


        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>): Any? {
            Log.e("Sion" , "method: ${method} , args: ${args}")
            return method?.invoke(iActivityManagerObject , *args)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        val activityTaskManagerSingleton = Class.forName("android.app.ActivityTaskManager").getDeclaredField("IActivityTaskManagerSingleton").apply {
            isAccessible = true
        }
        val defaultValue = activityTaskManagerSingleton.get(null)
        val mInstance = Class.forName("android.util.Singleton").getDeclaredField("mInstance").apply {
            isAccessible = true
        }

        val iActivityTaskManagerObject = mInstance.get(defaultValue)

        val IActivityManagerIntercept = Class.forName("android.app.IActivityTaskManager")
        val proxy = Proxy.newProxyInstance(
            Thread.currentThread().contextClassLoader,
            arrayOf(IActivityManagerIntercept),
            AmsInvocationHandler(iActivityTaskManagerObject)
        )

        mInstance.set(defaultValue , proxy)


        val activityThreadClass = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentActivityThread").invoke(null)
        val sPackageManager = Class.forName("android.app.ActivityThread").getDeclaredMethod("getPackageManager").invoke(null)
        val sPackageManager1 = Class.forName("android.app.ActivityThread").getDeclaredField("sPackageManager").apply {
            isAccessible = true
        }.get(activityThreadClass)



        Log.e("Sion" , "${iActivityTaskManagerObject}")
        Log.e("Sion" , "${sPackageManager}")
        Log.e("Sion" , "${sPackageManager1}")
    }
}