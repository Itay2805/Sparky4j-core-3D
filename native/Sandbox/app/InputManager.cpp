#include "InputManager.h"
#include "sp\app\Input.h"
#include "../handle.h"

using namespace sp;

JNIEXPORT jlong JNICALL Java_sp_app_Input_00024InputManager_jniCreate
(JNIEnv *env, jclass cls) {
	InputManager* manager = new InputManager();
	return getPointer(manager);
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1Update
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->Update();
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1PlatformUpdate
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->PlatformUpdate();
}

JNIEXPORT jboolean JNICALL Java_sp_app_Input_00024InputManager_native_1IsKeyPressed
(JNIEnv *env, jclass cls, jlong handler, jint keycode) {
	InputManager* manager = getHandle<InputManager>(handler);
	return manager->IsKeyPressed(keycode);
}

JNIEXPORT jboolean JNICALL Java_sp_app_Input_00024InputManager_native_1IsMousePressed
(JNIEnv *env, jclass cls, jlong handler, jint button) {
	InputManager* manager = getHandle<InputManager>(handler);
	return manager->IsMouseButtonPressed(button);
}

JNIEXPORT jboolean JNICALL Java_sp_app_Input_00024InputManager_native_1IsMouseButtonClicked
(JNIEnv *env, jclass cls, jlong handler, jint button) {
	InputManager* manager = getHandle<InputManager>(handler);
	return manager->IsMouseButtonClicked(button);
}

JNIEXPORT jfloat JNICALL Java_sp_app_Input_00024InputManager_native_1GetMousePosition_1X
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	return manager->GetMousePosition().x;
}

JNIEXPORT jfloat JNICALL Java_sp_app_Input_00024InputManager_native_1GetMousePosition_1Y
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	return manager->GetMousePosition().y;
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1SetMousePosition
(JNIEnv *env, jclass cls, jlong handler, jfloat x, jfloat y) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->SetMousePosition(maths::vec2(x, y));
}

JNIEXPORT jboolean JNICALL Java_sp_app_Input_00024InputManager_native_1IsMouseGrabbed
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	return manager->IsMouseGrabbed();
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1SetMouseGrabbed
(JNIEnv *env, jclass cls, jlong handler, jboolean grabbed) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->SetMouseGrabbed(grabbed);
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1SetMouseCursor
(JNIEnv *env, jclass cls, jlong handler, jint cursor) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->SetMouseCursor(cursor);
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1ClearKeys
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->ClearKeys();
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_native_1ClearMouseButtons
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	manager->ClearMouseButtons();
}

JNIEXPORT void JNICALL Java_sp_app_Input_00024InputManager_jniDelete
(JNIEnv *env, jclass cls, jlong handler) {
	InputManager* manager = getHandle<InputManager>(handler);
	delete manager;
}