package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import shared_files.Point;

public interface PointHandler extends Remote  {
	
	String getPointsInfo() throws RemoteException;
	double getIntervalLength(Point a, Point b) throws RemoteException;
	double getCircleLengthByRadius(Point a, Point b) throws RemoteException;
	double getCircleLengthByDiameter(Point a, Point b) throws RemoteException;
	double getCircleAreaByRadius(Point a, Point b) throws RemoteException;
	double getCircleAreaByDiameter(Point a, Point b) throws RemoteException;
	
}
