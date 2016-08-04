#include "Texture.h"
#include "../handle.h"
#include "sp\graphics\Texture.h"

using namespace sp::graphics;

JNIEXPORT void JNICALL Java_sp_graphics_Texture_native_1SetWrap
(JNIEnv *env, jclass clz, jint id) {
	Texture::SetWrap(static_cast<TextureWrap>(id));
}

JNIEXPORT void JNICALL Java_sp_graphics_Texture_native_1SetFilter
(JNIEnv *env, jclass clz, jint id) {
	Texture::SetFilter(static_cast<TextureFilter>(id));
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Texture_jniCreate__III
(JNIEnv *env, jclass clz, jint width, jint height, jint bits) {
	Texture* texture = new Texture(width, height, bits);
	return getPointer(texture);
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Texture_jniCreate__Ljava_lang_String_2
(JNIEnv *env, jclass clz, jstring filepath) {
	const char* filepathChars = env->GetStringUTFChars(filepath, NULL);
	Texture* texture = new Texture("", filepathChars);
	env->ReleaseStringUTFChars(filepath, filepathChars);
	return getPointer(texture);
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Texture_jniCreate__I
(JNIEnv *env, jclass clz, jint tid) {
	Texture* texture = new Texture(tid);
	return getPointer(texture);
}

JNIEXPORT void JNICALL Java_sp_graphics_Texture_native_1Bind
(JNIEnv *env, jclass clz, jlong handler) {
	Texture* texture = getHandle<Texture>(handler);
	texture->Bind();
}

JNIEXPORT void JNICALL Java_sp_graphics_Texture_native_1Unbind
(JNIEnv *env, jclass clz, jlong handler) {
	Texture* texture = getHandle<Texture>(handler);
	texture->Unbind();
}

JNIEXPORT jint JNICALL Java_sp_graphics_Texture_native_1GetID
(JNIEnv *env, jclass clz, jlong handler) {
	Texture* texture = getHandle<Texture>(handler);
	return texture->GetID();
}

JNIEXPORT jint JNICALL Java_sp_graphics_Texture_native_1GetWidth
(JNIEnv *env, jclass clz, jlong handler) {
	Texture* texture = getHandle<Texture>(handler);
	return texture->GetWidth();
}

JNIEXPORT jint JNICALL Java_sp_graphics_Texture_native_1GetHeight
(JNIEnv *env, jclass clz, jlong handler) {
	Texture* texture = getHandle<Texture>(handler);
	return texture->GetHeight();
}

JNIEXPORT void JNICALL Java_sp_graphics_Texture_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	Texture* texture = getHandle<Texture>(handler);
	delete texture;
}