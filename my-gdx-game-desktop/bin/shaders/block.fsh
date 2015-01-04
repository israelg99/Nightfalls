
varying vec4 vColor;
varying vec2 vTexCoord;

uniform vec2 screenSize;

uniform sampler2D tex;
uniform vec4 gameTime;

const float outerRadius = .65, innerRadius = .4, intensity = .6;

void main() {

	vec4 texColor = texture2D(tex, vTexCoord) * vColor;

	vec4 timedColor = (vColor + gameTime);

	vec2 relativePosition = gl_FragCoord.xy / screenSize - .5;

	float len = length(relativePosition);

	float vignette = smoothstep(outerRadius, innerRadius, len);

	texColor.rgb = mix(texColor.rgb, texColor.rgb * vignette, intensity);
		
	gl_FragColor = vec4(texColor.rgb * timedColor, texColor.a);
}