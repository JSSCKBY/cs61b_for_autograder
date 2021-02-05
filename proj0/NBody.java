public class NBody{

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] bodies = readPlanets(filename);
		// Drawing motionless section:
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		Planet.draw(bodies);
		//Deprecated
		//StdAudio.play("audio/2001.mid"); 
		StdDraw.enableDoubleBuffering();

		double t = 0; int NumBodies = readNumPlanets(filename);

		while(t <= T){

			Double[] xForces = new Double[NumBodies];
			Double[] yForces = new Double[NumBodies];

			for(int i = 0 ; i < NumBodies; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int i = 0; i < NumBodies; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			Planet.draw(bodies);
			StdDraw.show();
			StdDraw.pause(10);

			t = t + dt;
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}

	}

	public static double readRadius(String file){
		In in = new In(file);
		in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static int readNumPlanets(String file){
		In in = new In(file);
		int firstItemInFile = in.readInt();
		return firstItemInFile;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int N = in.readInt();
		double secondItemInFile = in.readDouble();
		Planet[] bodies = new Planet[N];

		for (int i = 0; i < N; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();

			bodies[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return bodies;
	}
}