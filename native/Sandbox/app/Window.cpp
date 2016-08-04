#include "Window.h"
#include "sp\app\Window.h"
#include "..\handle.h"

using namespace sp;

static JavaVM* g_vm = NULL;

static jclass eventBuilder = NULL;
static jclass window = NULL;
static jmethodID windowCallback = NULL;

static jmethodID createKeyPressedEvent = NULL;
static jmethodID createKeyReleaseEvent = NULL;

void callback(events::Event& event) {
	if (g_vm == NULL) {
		std::cout << "Error calling window callback event: JavaVM not initialized!" << std::endl;
		return;
	}

	JNIEnv* env;
	int getEnvStat = g_vm->GetEnv((void**)&env, JNI_VERSION_1_8);
	if (getEnvStat == JNI_EDETACHED) {
		std::cout << "GetEnv: not attached" << std::endl;
		if (g_vm->AttachCurrentThread((void**)&env, NULL) != 0) {
			std::cout << "Failed to attach" << std::endl;
			return;
		}
	}
	else if (getEnvStat == JNI_EVERSION) {
		std::cout << "GetEnv: version not supported (needs Java 8)" << std::endl;
		return;
	}

	if (env == NULL) {
		std::cout << "Error calling window callback event: JNIEnv not initialized!" << std::endl;
		return;
	}

	if (window == NULL) window = env->FindClass("sp/app/Window");
	if (window == NULL) {
		std::cout << "Error calling window callback event: sp.app.Window class not found" << std::endl;
		return;
	}

	if (eventBuilder == NULL) eventBuilder = env->FindClass("com/itay/wrapper/NativeEventBuilder");
	if (eventBuilder == NULL) {
		std::cout << "Error calling window callback event: com.itay.wrapper.NativeEventBuilder class not found" << std::endl;
		return;
	}

	if (windowCallback == NULL) windowCallback = env->GetStaticMethodID(window, "callEventCallback", "(JLsp/event/Event;)V");
	if (windowCallback == NULL) {
		std::cout << "Error calling window callback event: sp.app.Window.callEventCallback method not found" << std::endl;
		return;
	}

	if (createKeyPressedEvent == NULL) createKeyPressedEvent = env->GetStaticMethodID(eventBuilder, "createKeyPressedEvent", "(III)Lsp/event/KeyEvent$KeyPressedEvent;");
	if (createKeyPressedEvent == NULL) {
		std::cout << "Error calling window callback event: com.itay.wrapper.NativeEventBuilder.createKeyPressedEvent method not found" << std::endl;
		return;
	}

	if (createKeyReleaseEvent == NULL) createKeyReleaseEvent = env->GetStaticMethodID(eventBuilder, "createKeyReleaseEvent", "(I)Lsp/event/KeyEvent$KeyReleasedEvent;");
	if (createKeyReleaseEvent == NULL) {
		std::cout << "Error calling window callback event: com.itay.wrapper.NativeEventBuilder.createKeyReleaseEvent method not found" << std::endl;
		return;
	}

	jobject eventObj = NULL;
	switch (event.GetType()) {
	case events::Event::Type::KEY_PRESSED:
		do {
			events::KeyPressedEvent keyEvent = ((events::KeyPressedEvent&)event);
			eventObj = env->CallStaticObjectMethod(eventBuilder, createKeyPressedEvent, keyEvent.GetKeyCode(), keyEvent.GetRepeat(), keyEvent.GetModifiers());
		} while (false);
		break;
	case events::Event::Type::KEY_RELEASED:
		do {
			events::KeyReleasedEvent keyEvent = ((events::KeyReleasedEvent&)event);
			eventObj = env->CallStaticObjectMethod(eventBuilder, createKeyReleaseEvent, keyEvent.GetKeyCode());
		} while (false);
		break;
	case events::Event::Type::MOUSE_MOVED:
		// TODO: MOUSE_MOVED
		break;
	case events::Event::Type::MOUSE_PRESSED:
		// TODO: MOUSE_PRESSED
		break;
	case events::Event::Type::MOUSE_RELEASED:
		// TODO: MOUSE_RELEASED
		break;
	}

	if (eventObj != NULL) {
		// TODO: Make per window eventCallback
		env->CallStaticVoidMethod(window, windowCallback, (jlong)(0), eventObj);
	}
	if (env->ExceptionCheck()) {
		env->ExceptionDescribe();
	}
	
	g_vm->DetachCurrentThread();

}


JNIEXPORT jlong JNICALL Java_sp_app_Window_jniCreate
(JNIEnv *env, jclass cls, jstring title, jint width, jint height) {
	if (g_vm == NULL) {
		int status = env->GetJavaVM(&g_vm);
		if (status != 0) {
			std::cout << "Failed get JavaVM: " + status << std::endl;
		}
	}
	
	const char* titleChars = env->GetStringUTFChars(title, NULL);
	Window* window = new Window(titleChars, width, height);
	window->SetEventCallback(std::bind(&callback, std::placeholders::_1));
	env->ReleaseStringUTFChars(title, titleChars);
	return getPointer(window);
}

JNIEXPORT void JNICALL Java_sp_app_Window_native_1Clear
(JNIEnv *env, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	window->Clear();
}

JNIEXPORT void JNICALL Java_sp_app_Window_native_1Update
(JNIEnv *env, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	window->Update();

	/* This is for making the console work properly */
	std::cout << "" << std::flush;
}

JNIEXPORT jboolean JNICALL Java_sp_app_Window_native_1Closed
(JNIEnv *env, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	return window->Closed();
}

JNIEXPORT jint JNICALL Java_sp_app_Window_native_1GetWidth
(JNIEnv *emv, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	return window->GetWidth();
}

JNIEXPORT jint JNICALL Java_sp_app_Window_native_1GetHeight
(JNIEnv *env, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	return window->GetHeight();
}

JNIEXPORT jlong JNICALL Java_sp_app_Window_native_1GetInputManager
(JNIEnv *env, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	return getPointer(window->GetInputManager());
}

JNIEXPORT void JNICALL Java_sp_app_Window_jniDelete
(JNIEnv *env, jclass cls, jlong handler) {
	Window* window = getHandle<Window>(handler);
	delete window;
}