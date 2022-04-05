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


Here is the same math, as done in class:

Definition of a sphere
$(p-c)^2=R^2$

Definition of a ray
$r_o+r_d\cdot t$

Combining
$(r_o+r_d\cdot t - c)^2-R^2=0$

$(r_o+r_d\cdot t - c)\cdot (r_o+r_d\cdot t - c)-R^2=0$

$(r_o+r_d\cdot t - c)\cdot (r_o+r_d\cdot t - c)-R^2=0$

$r_o^2+r_d^2\cdot t^2+c^2 +2r_or_d\cdot t + -(2r_oc) + -r_dtc - R^2=0$

$r_d^2\cdot t^2 + t(2r_or_d -2r_dc)+r_o^2+c^2-2r_oc-R^2=0$

$A=r_d^2$

$B=2r_or_d -2r_dc$

Simplify B

$B=2r_d(r_o-c)$

$C=r_o^2+c^2-2r_oc-R^2$

Simplify C

$C=(r_o-c)^2-R^2$

Proof:
$(r_o-c)^2=?r_o^2+c^2-2r_oc$

$C=(r_o-c)^2-R^2$




Quadratic Formula
$(-B\pm \sqrt(B^2-4AC))/(2A)$

$(-(2r_or_d -2r_dc)\pm \sqrt((2r_or_d -2r_dc)^2-4r_d^2((r_o-c)^2-R^2)))/(2r_d^2)$

