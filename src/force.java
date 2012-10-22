import toxi.geom.*;

public class force {

	Vec3D loc;
	Vec3D vel;
	int weight;
	int hits;
	thesisWindErode p;
	
	boolean on = true;
	
	force(Vec3D _loc, Vec3D _vel, int _w, thesisWindErode parent){
		weight = _w;
		loc = _loc;
		vel = _vel;
		p = parent;
		hits = 0;
	}
	
	void update(){
		loc.addSelf(vel);
	}
	
	void draw(){
		p.gfx.sphere(new Sphere(loc, 10), 5);
	}
	
	
}
