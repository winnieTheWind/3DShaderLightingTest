varying vec2 v_texCoords;
varying vec3 v_vertPosWorld;
varying vec3 v_vertNVWorld;

uniform vec3      lightPosition;
uniform sampler2D u_texture;

void main()
{
    vec3  toLightVector  = normalize(lightPosition - v_vertPosWorld.xyz);
    float lightIntensity = max( 0.0, dot(v_vertNVWorld, toLightVector));
    vec4  texCol         = texture( u_texture, v_texCoords.st );
    gl_FragColor         = vec4( texCol.rgb * lightIntensity, 1.0 );
}