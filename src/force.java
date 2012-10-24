import java.util.ArrayList;

import toxi.geom.*;

public class force {

	Vec3D loc;
	Vec3D vel;
	int weight;
	int hits;
	thesisWindErode p;
	
	boolean on = true;
	
	force(Vec3D _loc, Vec3D _t, int _w, thesisWindErode parent){
		weight = _w;
		loc = _loc;
		vel = loc.copy().sub(_t).normalizeTo(-3);
		p = parent;
		hits = 0;
	}
	
	void update(){
		loc.addSelf(vel);
		ArrayList<Vec3D> pts = p.tree.getPointsWithinSphere(this.loc, p.tree.RAD);
		if(pts == null) return;
		Vec3D idR = new Vec3D();
		float minDist = 10000;
		for(Vec3D pt : pts){
			float dist = loc.distanceTo(pt);
			if(dist < minDist){
				minDist = dist;
				idR = pt;
			}
		}
		p.tree.remove(idR);
		this.hits++;
		if(this.hits >= this.weight) this.on = false; 
	}
	
	void draw(){
		p.gfx.sphere(new Sphere(loc, 3), 5);
	}
	
	
}
