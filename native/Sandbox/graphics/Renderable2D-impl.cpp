#include "Renderable2D-impl.h"

using namespace sp::graphics;
using namespace sp;

Renderable2D_impl::Renderable2D_impl()
	: Renderable2D(), position(m_Position), size(m_Size)
{}

Renderable2D_impl::Renderable2D_impl(const maths::vec3& position, const maths::vec2& size, uint color)
	: Renderable2D(position, size, color), position(m_Position), size(m_Size)
{}