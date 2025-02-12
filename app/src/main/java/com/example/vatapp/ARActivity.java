package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private String imageUrl; // Image URL from intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

        // Get the image URL from Intent
        imageUrl = getIntent().getStringExtra("imageUrl");

        // Tap to place the image in AR
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            if (imageUrl != null) {
                Anchor anchor = hitResult.createAnchor();
                loadImageAndPlaceInAR(anchor, imageUrl);
            }
        });
    }

    private void loadImageAndPlaceInAR(Anchor anchor, String imageUrl) {
        // Load the image as Bitmap using Glide
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        // Convert the bitmap to an AR texture
                        createArTexture(anchor, bitmap);
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        Toast.makeText(ARActivity.this, "Image loading failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createArTexture(Anchor anchor, Bitmap bitmap) {
        Texture.builder()
                .setSource(bitmap)
                .build()
                .thenAccept(texture -> {
                    MaterialFactory.makeTransparentWithTexture(this, texture)
                            .thenAccept(material -> {
                                ModelRenderable planeRenderable = ShapeFactory.makeCube(
                                        new Vector3(0.3f, 0.01f, 0.3f), // Adjust size of AR image
                                        new Vector3(0f, 0.01f, 0f),
                                        material
                                );
                                addNodeToScene(anchor, planeRenderable);
                            });
                });
    }

    private void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
