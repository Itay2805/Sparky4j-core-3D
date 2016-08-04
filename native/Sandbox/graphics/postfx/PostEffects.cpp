#include "PostEffects.h"
#include "../../handle.h"
#include "sp\graphics\postfx\PostEffects.h"

using namespace sp::graphics;

JNIEXPORT void JNICALL Java_sp_graphics_postfx_PostEffects_native_1Push
(JNIEnv *env, jclass clz, jlong handler, jlong passPointer) {
	PostEffects* postfx = getHandle<PostEffects>(handler);
	PostEffectsPass* pass = getHandle<PostEffectsPass>(passPointer);
	postfx->Push(pass);
}

JNIEXPORT void JNICALL Java_sp_graphics_postfx_PostEffects_native_1Pop
(JNIEnv *env, jclass clz, jlong handler) {
	PostEffects* postfx = getHandle<PostEffects>(handler);
	postfx->Pop();
}

JNIEXPORT jlong JNICALL Java_sp_graphics_postfx_PostEffects_jniCreate
(JNIEnv *env, jclass clz) {
	PostEffects* postfx = new PostEffects();
	return getPointer(postfx);
}

JNIEXPORT void JNICALL Java_sp_graphics_postfx_PostEffects_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	PostEffects* postfx = getHandle<PostEffects>(handler);
	delete postfx;
}