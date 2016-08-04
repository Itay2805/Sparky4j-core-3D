#include "Renderable2D.h"
#include "Renderable2D-impl.h"
#include "../handle.h"

JNIEXPORT jlong JNICALL Java_sp_graphics_Renderable2D_jniCreate__
(JNIEnv *env, jclass clz) {
	Renderable2D_impl* renderable = new Renderable2D_impl();
	return getPointer(renderable);
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Renderable2D_jniCreate__FFFFFI
(JNIEnv *env, jclass clz, jfloat xPos, jfloat yPos, jfloat zPos, jfloat xSize, jfloat ySize, jint color) {
	Renderable2D_impl* renderable = new Renderable2D_impl(maths::vec3(xPos, yPos, zPos), maths::vec2(xSize, ySize), color);
	return getPointer(renderable);
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Renderable2D_native_1GetPosition_1X
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->position.x;
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Renderable2D_native_1GetPosition_1Y
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->position.y;
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Renderable2D_native_1GetPosition_1Z
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->position.z;
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderable2D_native_1SetPosition
(JNIEnv *env, jclass clz, jlong handler, jfloat x, jfloat y, jfloat z) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	renderable->position.x = x;
	renderable->position.y = y;
	renderable->position.z = z;
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Renderable2D_native_1GetSize_1X
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->size.x;
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Renderable2D_native_1GetSize_1Y
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->size.y;
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderable2D_native_1SetSize
(JNIEnv *env, jclass clz, jlong handler, jfloat x, jfloat y) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	renderable->size.x = x;
	renderable->size.y = y;
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderable2D_native_1SetColor
(JNIEnv *env, jclass clz, jlong handler, jint color) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	renderable->SetColor(color);
}

JNIEXPORT jint JNICALL Java_sp_graphics_Renderable2D_native_1GetColor
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->GetColor();
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Renderable2D_native_1GetTexture
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return getPointer(renderable->GetTexture());
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderable2D_native_1SetTexture
(JNIEnv *env, jclass clz, jlong handler, jlong textureHandler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	Texture* texture = getHandle<Texture>(textureHandler);
	renderable->SetTexture(texture);
}

JNIEXPORT jboolean JNICALL Java_sp_graphics_Renderable2D_native_1IsVisible
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	return renderable->IsVisible();
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderable2D_native_1SetVisible
(JNIEnv *env, jclass clz, jlong handler, jboolean visible) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	renderable->SetVisible(visible);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderable2D_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	Renderable2D_impl* renderable = getHandle<Renderable2D_impl>(handler);
	delete renderable;
}