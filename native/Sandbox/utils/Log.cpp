#include "Log.h"
#include "sp\utils\Log.h"

JNIEXPORT void JNICALL Java_sp_utils_Log_SP_1FATAL
(JNIEnv *env, jclass cls, jstring text) {
	const char* textChars = env->GetStringUTFChars(text, NULL);
	SP_FATAL(textChars);
	env->ReleaseStringUTFChars(text, textChars);
}

JNIEXPORT void JNICALL Java_sp_utils_Log_SP_1ERROR
(JNIEnv *env, jclass cls, jstring text) {
	const char* textChars = env->GetStringUTFChars(text, NULL);
	SP_ERROR(textChars);
	env->ReleaseStringUTFChars(text, textChars);
}

JNIEXPORT void JNICALL Java_sp_utils_Log_SP_1WARN
(JNIEnv *env, jclass cls, jstring text) {
	const char* textChars = env->GetStringUTFChars(text, NULL);
	SP_WARN(textChars);
	env->ReleaseStringUTFChars(text, textChars);
}

JNIEXPORT void JNICALL Java_sp_utils_Log_SP_1INFO
(JNIEnv *env, jclass cls, jstring text) {
	const char* textChars = env->GetStringUTFChars(text, NULL);
	SP_INFO(textChars);
	env->ReleaseStringUTFChars(text, textChars);
}