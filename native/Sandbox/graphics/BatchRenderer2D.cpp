#include "BatchRenderer2D.h"
#include "sp\graphics\BatchRenderer2D.h"
#include "../handle.h"

using namespace sp::graphics;

JNIEXPORT jlong JNICALL Java_sp_graphics_BatchRenderer2D_jniCreate
(JNIEnv *env, jclass clz, jint width, jint height) {
	BatchRenderer2D* renderer = new BatchRenderer2D(width, height);
	return getPointer(renderer);
}