#pragma once

#include <jni.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void freeBuffer(JNIEnv *env, jobject obj_buffer) {
	unsigned char* buffer = (unsigned char*)(obj_buffer ? env->GetDirectBufferAddress(obj_buffer) : 0);
	free(buffer);
}

void* getBufferData(JNIEnv *env, jobject obj_buffer) {
	void* buffer = (void*)(obj_buffer ? env->GetDirectBufferAddress(obj_buffer) : 0);
	return buffer;
}

long getBufferPointer(JNIEnv *env, jobject obj_buffer) {
	unsigned char* buffer = (unsigned char*)getBufferData(env, obj_buffer);
	jlong JNI_returnValue = (jlong)buffer;
	return JNI_returnValue;
}