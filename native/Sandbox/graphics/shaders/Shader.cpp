#include "Shader.h"
#include "../../handle.h"
#include "../../memory.h"
#include "sp\graphics\shaders\Shader.h"

using namespace sp::graphics;

JNIEXPORT jlong JNICALL Java_sp_graphics_shaders_Shader_jniCreate
(JNIEnv *env, jclass clz, jstring name, jstring source) {
	const char* sourceChars = env->GetStringUTFChars(source, NULL);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	Shader* shader = new Shader(nameChars, sourceChars);
	env->ReleaseStringUTFChars(source, sourceChars);
	env->ReleaseStringUTFChars(name, nameChars);
	return getPointer(shader);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_jniDelete
(JNIEnv *env, jclass clz, jlong handler) {
	Shader* shader = getHandle<Shader>(handler);
	delete shader;
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform1f
(JNIEnv *env, jclass clz, jlong handler, jstring name, jfloat value) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	shader->SetUniform1f(nameChars, value);
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform1fv
(JNIEnv *env, jclass clz, jlong handler, jstring name, jobject buffer, jint count) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	float* values = (float*) getBufferData(env, buffer);
	shader->SetUniform1fv(nameChars, values, count);
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform1i
(JNIEnv *env, jclass clz, jlong handler, jstring name, jint value) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	shader->SetUniform1i(nameChars, value);
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform1iv
(JNIEnv *env, jclass clz, jlong handler, jstring name, jobject buffer, jint count) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	int* values = (int*)getBufferData(env, buffer);
	shader->SetUniform1iv(nameChars, values, count);
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform2f
(JNIEnv *env, jclass clz, jlong handler, jstring name, jfloat x, jfloat y) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	shader->SetUniform2f(nameChars, sp::maths::vec2(x, y));
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform3f
(JNIEnv *env, jclass clz, jlong handler, jstring name, jfloat x, jfloat y, jfloat z) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	shader->SetUniform3f(nameChars, sp::maths::vec3(x, y, z));
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform4f
(JNIEnv *env, jclass clz, jlong handler, jstring name, jfloat x, jfloat y, jfloat z, jfloat w) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	shader->SetUniform4f(nameChars, sp::maths::vec4(x, y, z, w));
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniformMat4
(JNIEnv *env, jclass clz, jlong handler, jstring name, jobject buffer) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	float* elements = (float*)getBufferData(env, buffer);
	shader->SetUniformMat4(nameChars, sp::maths::mat4(elements));
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1SetUniform
(JNIEnv *env, jclass clz, jlong handler, jstring name, jobject buffer) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	byte* data = (byte*)getBufferData(env, buffer);
	shader->SetUniform(nameChars, data);
	env->ReleaseStringUTFChars(name, nameChars);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1ResolveAndSetUniform
(JNIEnv *env, jclass clz, jlong handler, jint index, jobject buffer) {
	Shader* shader = getHandle<Shader>(handler);
	byte* data = (byte*)getBufferData(env, buffer);
	shader->ResolveAndSetUniform(index, data);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1ResolveAndSetUniforms
(JNIEnv *env, jclass clz, jlong handler, jobject buffer, jint size) {
	Shader* shader = getHandle<Shader>(handler);
	byte* data = (byte*)getBufferData(env, buffer);
	shader->ResolveAndSetUniforms(data, size);
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1Bind
(JNIEnv *env, jclass clz, jlong handler) {
	Shader* shader = getHandle<Shader>(handler);
	shader->Bind();
}

JNIEXPORT void JNICALL Java_sp_graphics_shaders_Shader_native_1Unbind
(JNIEnv *env, jclass clz, jlong handler) {
	Shader* shader = getHandle<Shader>(handler);
	shader->Unbind();
}

JNIEXPORT jboolean JNICALL Java_sp_graphics_shaders_Shader_native_1HashUniform
(JNIEnv *env, jclass clz, jlong handler, jstring name) {
	Shader* shader = getHandle<Shader>(handler);
	const char* nameChars = env->GetStringUTFChars(name, NULL);
	bool ret = shader->HasUniform(nameChars);
	env->ReleaseStringUTFChars(name, nameChars);
	return ret;
}