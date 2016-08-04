#pragma once

#include "sp\graphics\Renderable2D.h"

using namespace sp::graphics;
using namespace sp;

class Renderable2D_impl : public Renderable2D
{
public:
	maths::vec3& position;
	maths::vec2& size;
public:
	Renderable2D_impl();
	Renderable2D_impl(const maths::vec3& position, const maths::vec2& size, uint color);

	inline void SetTexture(Texture* texture) { m_Texture = texture; }
};