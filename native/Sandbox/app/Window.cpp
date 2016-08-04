#include "Window.h"
#include "sp\app\Window.h"
#include "..\handle.h"

using namespace sp;

static void test(events::Event& event) {
	// TODO: Somehow find a way to set a consumer callback
}


JNIEXPORT jlong JNICALL Java_sp_app_Window_jniCreate
(JNIEnv *env, jclass cls, jstring title, jint width, jint height) {
	const char* titleChars = env->GetStringUTFChars(title, NULL);
	Window* window = new Window(titleChars, width, height);
	window->SetEventCallback(std::bind(&test, std::placeholders::_1));
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