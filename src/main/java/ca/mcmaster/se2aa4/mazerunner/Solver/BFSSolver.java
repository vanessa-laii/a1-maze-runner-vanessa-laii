package ca.mcmaster.se2aa4.mazerunner.Solver;


import java.util.*;

import ca.mcmaster.se2aa4.mazerunner.Tools.*;

public class BFSSolver implements MazeSolver {
    private Maze maze;

    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        Map<Position, Position> parentMap = new HashMap<>();
        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
    
        Position start = maze.getStart();
        Position end = maze.getEnd();
    
        queue.add(start);
        visited.add(start);
    
        while (!queue.isEmpty()) {
            Position currentPos = queue.poll();    
            if (currentPos.equals(end)) {
                return reconstructPath(parentMap, start, end);
            }
            List<Position> neighbors = getMazeNeighbors(currentPos);
            for (Position neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    parentMap.put(neighbor, currentPos);
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return new Path();
    }

    /**
     * Make a decision of what is required.
     *
     * @param parentMap HashMap of the tiles and its previous tile
     * @param start Start position of the map
     * @param end End position of the map 
     */
    private Path reconstructPath(Map<Position, Position> parentMap, Position start, Position end) {
        StringBuilder stringPath = new StringBuilder();
        Position currentPos = end;
        Direction currentDirection = Direction.RIGHT; 

        while (!currentPos.equals(start)) {
            Position parentPos = parentMap.get(currentPos);

            Direction direction = currentDirection.getDirection(parentPos, currentPos);

            if (direction == currentDirection) {
                stringPath.append('F');
            } else if (direction == direction.getRelativeRight(currentDirection)) {
                stringPath.append('L');
                stringPath.append('F');
            } else if (direction == direction.getRelativeLeft(currentDirection)) {
                stringPath.append('R');
                stringPath.append('F');
            } else {
                throw new IllegalArgumentException("Invalid direction");
            }

            currentPos = parentPos;
            currentDirection = direction;
        }
        return new Path(stringPath.reverse().toString());
    }

    private List<Position> getMazeNeighbors(Position pos) {
        List<Position> neighbors = new ArrayList<>();

        Position left = pos.add(new Position(-1, 0));
        if (left.x() >= 0 && !maze.isWall(left)) {
            neighbors.add(left);
        }

        Position right = pos.add(new Position(1, 0));
        if (right.x() < maze.getSizeX() && !maze.isWall(right)) {
            neighbors.add(right);
        }

        Position up = pos.add(new Position(0, -1));
        if (up.y() >= 0 && !maze.isWall(up)) {
            neighbors.add(up);
        }

        Position down = pos.add(new Position(0, 1));
        if (down.y() < maze.getSizeY() && !maze.isWall(down)) {
            neighbors.add(down);
        }

        return neighbors;
    }

}
