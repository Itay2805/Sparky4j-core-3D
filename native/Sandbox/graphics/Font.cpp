#include "Font.h"
#include "../handle.h"
#include "../memory.h"
#include "sp\graphics\Font.h"

using namespace sp::graphics;

JNIEXPORT jlong JNICALL Java_sp_graphics_Font_jniCreate__Ljava_lang_String_2F
(JNIEnv *env, jclass clz, jstring filepath, jfloat size) {
	const char* filepathChars = env->GetStringUTFChars(filepath, NULL);
	Font* font = new Font("", filepathChars, size);
	env->ReleaseStringUTFChars(filepath, filepathChars);
	return getPointer(font);
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Font_jniCreate__Ljava_nio_Buffer_2IF
(JNIEnv *env, jclass clz, jobject buffer, jint datasize, jfloat size) {
	const byte* data = (byte*) getBufferData(env, buffer);
	Font* font = new Font("", data, datasize, size);
	return getPointer(font);
}

JNIEXPORT void JNICALL Java_sp_graphics_Font_native_1SetScale
(JNIEnv *env, jclass clz, jlong handler, jfloat x, jfloat y) {
	Font* font = getHandle<Font>(handler);
	font->SetScale(sp::maths::vec2(x, y));
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Font_native_1GetScale_1X
(JNIEnv *env, jclass clz, jlong handler) {
	Font* font = getHandle<Font>(handler);
	return font->GetScale().x;
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Font_native_1GetScale_1Y
(JNIEnv *env, jclass clz, jlong handler) {
	Font* font = getHandle<Font>(handler);
	return font->GetScale().y;
}

JNIEXPORT jfloat JNICALL Java_sp_graphics_Font_native_1GetSize
(JNIEnv *env, jclass clz, jlong handler) {
	Font* font = getHandle<Font>(handler);
	return font->GetSize();
}

JNIEXPORT jlong JNICALL Java_sp_graphics_Font_native_1GetTexture
(JNIEnv *env, jclass clz, jlong handler) {
	Font* font = getHandle<Font>(handler);
	Texture* texture = font->GetTexture();
	return getPointer(texture);
}

JNIEXPORT jint JNICALL Java_sp_graphics_Font_native_1GetID
(JNIEnv *env, jclass clz, jlong handler) {
	Font* font = getHandle<Font>(handler);
	return font->GetID();
}

JNIEXPORT void JNICALL Java_sp_graphics_Font_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	Font* font = getHandle<Font>(handler);
	delete font;
}