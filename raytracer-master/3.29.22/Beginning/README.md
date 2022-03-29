# Ray Tracer

This repo will hold our code from consecutive days as we learn about ray tracing.

The first items we will cover will be about math in general. Then we will start coving ray tracing specifically.

## Sphere intersections

Given a sphere centered about $c$ with a radius of $r$, and given a ray ($ray$) with origin $o$ and direction $d$, the ray intersects the sphere at:

$(ray-c)\cdot (ray-c)-R^2=0$

$(o+dt-c)\cdot (o+dt-c)-R^2=0$

$(o+dt-c)\cdot (o+dt-c)-R^2=0$

$o\cdot o+o\cdot d t-o\cdot c+d\cdot dt^2-c\cdot dt-o\cdot c-c\cdot dt+c\cdot c-R^2=0$

$(o\cdot o)+(o\cdot d)t-2(o\cdot c)+(d\cdot d)t^2-2(c\cdot d)t+(c\cdot c)-R^2=0$

$t^2(d\cdot d)+t(o\cdot d)+-2t(c\cdot d)+(c\cdot c)-R^2+(o\cdot o)-2(o\cdot c)=0$

$t^2(d\cdot d)+t(o\cdot d)+t(-2\cdot c\cdot d)+(c\cdot c)-R^2+(o\cdot o)-2(o\cdot c)=0$

$t^2(d\cdot d)+t(o\cdot d-2\cdot c\cdot d)+(c\cdot c)-R^2+(o\cdot o)-2(o\cdot c)=0$

$(d\cdot d)t^2+2d(o-c)t+(o-c)\cdot(o-c)-R^2=0$

Quadratic Equation

${-b\pm\sqrt{b^2-4ac}}/{2a}$

${-(2d(o-c))\pm\sqrt{(2d(o-c))^2-4(d\cdot d)((o-e)\cdot(o-c)-R^2))}}/{2(d\cdot d)}$




