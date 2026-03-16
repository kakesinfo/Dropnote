package com.dropnote.cloud.core.events;

import java.lang.reflect.*;
import java.util.*;

/**
 * 
 * @author KakesInfo
 *
 */
public class EventDispatcher
{
	private final Map<Class<?>, List<RegisteredListener>> listeners;

	public EventDispatcher()
	{
		listeners = Collections.synchronizedMap(new HashMap<>());
	}

	/**
	 * 
	 * @param listener The listener we want to listen to
	 * 
	 */
	public void register(Listener<?> listener)
	{
		Class<?> c = listener.getClass();
		for (Method m : c.getMethods())
		{
			if (m.getAnnotation(EventHandler.class) != null && m.getParameterTypes().length == 1
					&& Event.class.isAssignableFrom(m.getParameterTypes()[0]))
			{
				RegisteredListener reglistener = new RegisteredListener(listener, m);
				Class<?> eventType = m.getParameterTypes()[0];
				List<RegisteredListener> methods = listeners.get(eventType);
				if (methods == null)
				{
					methods = new ArrayList<>();
					listeners.put(eventType, methods);
				}
				methods.add(reglistener);
			}
		}
	}

	/**
	 * 
	 * @param event The event we'll call and that the right listener will listen
	 * 
	 */
	public void call(Event event)
	{
		List<RegisteredListener> methods = new ArrayList<>();
		Class<?> eventType = event.getClass();
		while (true)
		{
			List<RegisteredListener> m = listeners.get(eventType);
			if (m != null)
			{
				methods.addAll(m);
			}
			if (eventType == Event.class)
			{
				break;
			}
			eventType = eventType.getSuperclass();
		}
		for (RegisteredListener method : methods)
		{
			try
			{
				method.handleEvent(event);
			}
			catch (Throwable t)
			{

			}
		}
	}

	/**
	 * 
	 * @author KakesInfo
	 *
	 */
	public class RegisteredListener
	{
		private final Listener<?> listener;
		private final Method method;

		/**
		 * 
		 * @param l The listener
		 * @param m The listener's method
		 */
		public RegisteredListener(Listener<?> l, Method m)
		{
			this.listener = l;
			this.method = m;
			this.method.setAccessible(true);
		}
		
		/**
		 * 
		 * @param e
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 */
		public void handleEvent(Event e) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
			this.method.invoke(listener, e);
		}
		
		/**
		 * 
		 * @return
		 */
		public Method getMethod()
		{
			return this.method;
		}
	}
}
