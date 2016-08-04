#include "PostEffectsPass.h"
#include "../../handle.h"
#include "sp\graphics\postfx\PostEffectsPass.h"

using namespace sp::graphics;

JNIEXPORT jlong JNICALL Java_sp_graphics_postfx_PostEffectsPass_jniCreate
(JNIEnv *env, jclass clz, jlong shaderPointer) {
	Shader* shader = getHandle<Shader>(shaderPointer);
	PostEffectsPass* pass = new PostEffectsPass(shader);
	return getPointer(pass);
}

JNIEXPORT void JNICALL Java_sp_graphics_postfx_PostEffectsPass_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	PostEffectsPass* pass = getHandle<PostEffectsPass>(handler);
	delete pass;
}