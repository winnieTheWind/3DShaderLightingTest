package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TestShader implements Shader {

    ShaderProgram program;
    Camera camera;
    RenderContext context;

    private Vector3 lightPosition;
    private int lightColour;

    @Override
    public void init () {
        String vert = Gdx.files.internal("shaders/lambertvertex.glsl").readString();
        String frag = Gdx.files.internal("shaders/lambertfragment.glsl").readString();
        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled())
            throw new GdxRuntimeException(program.getLog());


    }
    @Override
    public void dispose () {
        program.dispose();
    }
    @Override
    public void begin (Camera camera, RenderContext context) {
        this.camera = camera;
        this.context = context;
        program.begin();
        program.setUniformMatrix("u_projViewTrans", camera.combined);
        program.setUniformi("u_texture", 0);





        context.setDepthTest(GL20.GL_LEQUAL, 0f, 1f);
        context.setCullFace(GL20.GL_BACK);

    }

    @Override
    public void render (Renderable renderable) {
        program.setUniformMatrix("u_worldTrans", renderable.worldTransform);
        renderable.meshPart.render(program);
    }
    @Override
    public void end () {
        program.end();
    }
    @Override
    public int compareTo (Shader other) {
        return 0;
    }
    @Override
    public boolean canRender (Renderable instance) {
        return true;
    }
}
