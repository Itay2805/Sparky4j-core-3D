#include "Renderer2D.h"
#include "../handle.h"
#include "../memory.h"
#include "sp\graphics\Renderer2D.h"

using namespace sp::graphics;

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1Push
(JNIEnv *env, jclass clz, jlong handler, jobject buffer, jboolean ovveride) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	float* elements = (float*)getBufferData(env, buffer);
	sp::maths::mat4 matrix(elements);
	renderer->Push(matrix, ovveride);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1Pop
(JNIEnv *env, jclass clz, jlong handler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->Pop();
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1SetRenderTarget
(JNIEnv *env, jclass clz, jlong handler, jint id) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	switch (id) {
		case 0: renderer->SetRenderTarget(RenderTarget::SCREEN);
		case 1: renderer->SetRenderTarget(RenderTarget::BUFFER);
	}
}

JNIEXPORT jint JNICALL Java_sp_graphics_Renderer2D_native_1GetRenderTarget
(JNIEnv *env, jclass clz, jlong handler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	RenderTarget target = renderer->GetRenderTarget();
	switch (target) {
		case RenderTarget::SCREEN: return 0;
		case RenderTarget::BUFFER: return 1;
	}
	return -1;
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1Begin
(JNIEnv *env, jclass clz, jlong handler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->Begin();
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1Submit
(JNIEnv *env, jclass clz, jlong handler, jlong renderableHandler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	Renderable2D* renderable = getHandle<Renderable2D>(renderableHandler);
	renderer->Submit(renderable);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1DrawLine
(JNIEnv *env, jclass clz, jlong handler, jfloat x0, jfloat y0, jfloat x1, jfloat y1, jfloat thickness, jint color) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->DrawLine(x0, y0, x1, y1, thickness, color);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1DrawRect
(JNIEnv *env, jclass clz, jlong handler, jfloat x, jfloat y, jfloat width, jfloat height, jint color) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->DrawRect(x, y, width, height, color);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1DrawString
(JNIEnv *env, jclass clz, jlong handler, jstring text, jfloat x, jfloat y, jlong fontPointer, jint color) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	Font* font = getHandle<Font>(fontPointer);
	const char* textChars = env->GetStringUTFChars(text, NULL);
	renderer->DrawString(textChars, sp::maths::vec2(x, y), *font, color);
	env->ReleaseStringUTFChars(text, textChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1FillRect
(JNIEnv *env, jclass clz, jlong handler, jfloat x, jfloat y, jfloat width, jfloat height, jint color) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->FillRect(x, y, width, height, color);
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1End
(JNIEnv *env, jclass clz, jlong handler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->End();
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_native_1Present
(JNIEnv *env, jclass clz, jlong handler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	renderer->Present();
}

JNIEXPORT void JNICALL Java_sp_graphics_Renderer2D_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	Renderer2D* renderer = getHandle<Renderer2D>(handler);
	delete renderer;
}