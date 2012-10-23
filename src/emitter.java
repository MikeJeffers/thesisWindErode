import java.util.Vector;
import toxi.geom.*;

public class emitter {

	thesisWindErode p;
	Vec3D loc, dir;
	int range;
	Vector<force> forces = new Vector<force>();

	emitter(Vec3D d, Vec3D _loc, int r, thesisWindErode parent) {
		dir = d;
		loc = _loc;
		p = parent;
		range = r;

		for (int i = 0; i < 25; i++) {
			forces.add(new force(loc.copy().jitter(50), dir, 1, p));
		}
	}

	void update() {
		Vector<force> usedForces = new Vector<force>();
		for (force f : forces) {
			f.update();
			if(f.on == false) usedForces.add(f);
		}
		forces.removeAll(usedForces);
	}

	void draw() {
		p.fill(255, 0, 0);
		p.gfx.box(new AABB(loc, range));

		for (force f : forces) {
			f.draw();
		}
	}

}
