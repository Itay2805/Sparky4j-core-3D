#include "memory.h"
#include "../memory.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

JNIEXPORT jobject JNICALL Java_com_itay_wrapper_Memory_mallocJni
(JNIEnv *env, jclass clz, jint numBytes) {
	char* ptr = (char*)malloc(numBytes);
	if (ptr == 0) return 0;
	return env->NewDirectByteBuffer(ptr, numBytes);
}

JNIEXPORT void JNICALL Java_com_itay_wrapper_Memory_free
(JNIEnv *env, jclass clz, jobject obj_buffer) {
	freeBuffer(env, obj_buffer);
}

JNIEXPORT jlong JNICALL Java_com_itay_wrapper_Memory_getBufferAddress
(JNIEnv *env, jclass clz, jobject obj_buffer) {
	return (jlong)getBufferPointer(env, obj_buffer);
}