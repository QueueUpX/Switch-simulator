package apps.omega;

import apps.delta.Connection;
import apps.delta.Label;
import apps.delta.Line;
import apps.delta.MyPanel;

import java.awt.*;
import java.util.ArrayList;

public class MyPanel1 extends MyPanel {
    @Override
    protected void drawTitle(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.black);
        g.drawString("OMEGA switch networks",
                640 - g.getFontMetrics().stringWidth("OMEGA switch networks"), g.getFontMetrics().getHeight());
    }

    @Override
    protected void makeConnections() {
        // ***************************************   CONNECTION 0   *************************************************

        connections.get(0).addLine(new Line(labels.get(0).getPin().x2, labels.get(0).getPin().y2,
                comRects.get(0).getPinLeftUp().x2, comRects.get(0).getPinLeftUp().y2));
        connections.get(0).addLine(new Line(comRects.get(0).getPinRightUp().x2, comRects.get(0).getPinRightUp().y2,
                comRects.get(1).getPinLeftUp().x2, comRects.get(1).getPinLeftUp().y2));
        connections.get(0).addLine(new Line(comRects.get(1).getPinRightUp().x2, comRects.get(1).getPinRightUp().y2,
                comRects.get(2).getPinLeftUp().x2, comRects.get(2).getPinLeftUp().y2));
        connections.get(0).addLine(new Line(comRects.get(2).getPinRightUp().x2, comRects.get(2).getPinRightUp().y2,
                labels.get(8).getPin().x2, labels.get(8).getPin().y2));
        connections.get(0).setRoad(new int[]{0, 0, 0, 0});

        // ***************************************   CONNECTION 1   *************************************************

        connections.get(1).addLine(new Line(labels.get(1).getPin().x2, labels.get(1).getPin().y2,
                comRects.get(3).getPinLeftUp().x2, comRects.get(3).getPinLeftUp().y2));
        connections.get(1).addLine(new Line(comRects.get(3).getPinRightUp().x2, comRects.get(3).getPinRightUp().y2,
                comRects.get(7).getPinLeftUp().x2, comRects.get(7).getPinLeftUp().y2));
        connections.get(1).addLine(new Line(comRects.get(7).getPinRightUp().x2, comRects.get(7).getPinRightUp().y2,
                comRects.get(2).getPinLeftDown().x2, comRects.get(2).getPinLeftDown().y2));
        connections.get(1).addLine(new Line(comRects.get(2).getPinRightDown().x2, comRects.get(2).getPinRightDown().y2,
                labels.get(10).getPin().x2, labels.get(10).getPin().y2));
        connections.get(0).setRoad(new int[]{1, 0, 0});

        // ***************************************   CONNECTION 2   *************************************************

        connections.get(2).addLine(new Line(labels.get(2).getPin().x2, labels.get(2).getPin().y2,
                comRects.get(6).getPinLeftUp().x2, comRects.get(6).getPinLeftUp().y2));
        connections.get(2).addLine(new Line(comRects.get(6).getPinRightUp().x2, comRects.get(6).getPinRightUp().y2,
                comRects.get(1).getPinLeftDown().x2, comRects.get(1).getPinLeftDown().y2));
        connections.get(2).addLine(new Line(comRects.get(1).getPinRightDown().x2, comRects.get(1).getPinRightDown().y2,
                comRects.get(5).getPinLeftUp().x2, comRects.get(5).getPinLeftUp().y2));
        connections.get(2).addLine(new Line(comRects.get(5).getPinRightUp().x2, comRects.get(5).getPinRightUp().y2,
                labels.get(12).getPin().x2, labels.get(12).getPin().y2));
        connections.get(0).setRoad(new int[]{0, 0, 1});

        // ***************************************   CONNECTION 3   *************************************************

        connections.get(3).addLine(new Line(labels.get(3).getPin().x2, labels.get(3).getPin().y2,
                comRects.get(9).getPinLeftUp().x2, comRects.get(9).getPinLeftUp().y2));
        connections.get(3).addLine(new Line(comRects.get(9).getPinRightUp().x2, comRects.get(9).getPinRightUp().y2,
                comRects.get(7).getPinLeftDown().x2, comRects.get(7).getPinLeftDown().y2));
        connections.get(3).addLine(new Line(comRects.get(7).getPinRightDown().x2, comRects.get(7).getPinRightDown().y2,
                comRects.get(5).getPinLeftDown().x2, comRects.get(5).getPinLeftDown().y2));
        connections.get(3).addLine(new Line(comRects.get(5).getPinRightDown().x2, comRects.get(5).getPinRightDown().y2,
                labels.get(14).getPin().x2, labels.get(14).getPin().y2));
        connections.get(0).setRoad(new int[]{1, 0, 1});

        // ***************************************   CONNECTION 4   *************************************************

        connections.get(4).addLine(new Line(labels.get(4).getPin().x2, labels.get(4).getPin().y2,
                comRects.get(0).getPinLeftDown().x2, comRects.get(0).getPinLeftDown().y2));
        connections.get(4).addLine(new Line(comRects.get(0).getPinRightDown().x2, comRects.get(0).getPinRightDown().y2,
                comRects.get(4).getPinLeftUp().x2, comRects.get(4).getPinLeftUp().y2));
        connections.get(4).addLine(new Line(comRects.get(4).getPinRightUp().x2, comRects.get(4).getPinRightUp().y2,
                comRects.get(8).getPinLeftUp().x2, comRects.get(8).getPinLeftUp().y2));
        connections.get(4).addLine(new Line(comRects.get(8).getPinRightUp().x2, comRects.get(8).getPinRightUp().y2,
                labels.get(9).getPin().x2, labels.get(9).getPin().y2));
        connections.get(0).setRoad(new int[]{0, 1, 0});

        // ***************************************   CONNECTION 5   *************************************************

        connections.get(5).addLine(new Line(labels.get(5).getPin().x2, labels.get(5).getPin().y2,
                comRects.get(3).getPinLeftDown().x2, comRects.get(3).getPinLeftDown().y2));
        connections.get(5).addLine(new Line(comRects.get(3).getPinRightDown().x2, comRects.get(3).getPinRightDown().y2,
                comRects.get(10).getPinLeftUp().x2, comRects.get(10).getPinLeftUp().y2));
        connections.get(5).addLine(new Line(comRects.get(10).getPinRightUp().x2, comRects.get(10).getPinRightUp().y2,
                comRects.get(8).getPinLeftDown().x2, comRects.get(8).getPinLeftDown().y2));
        connections.get(5).addLine(new Line(comRects.get(8).getPinRightDown().x2, comRects.get(8).getPinRightDown().y2,
                labels.get(11).getPin().x2, labels.get(11).getPin().y2));
        connections.get(0).setRoad(new int[]{1, 1, 0});

        // ***************************************   CONNECTION 6   *************************************************

        connections.get(6).addLine(new Line(labels.get(6).getPin().x2, labels.get(6).getPin().y2,
                comRects.get(6).getPinLeftDown().x2, comRects.get(6).getPinLeftDown().y2));
        connections.get(6).addLine(new Line(comRects.get(6).getPinRightDown().x2, comRects.get(6).getPinRightDown().y2,
                comRects.get(4).getPinLeftDown().x2, comRects.get(4).getPinLeftDown().y2));
        connections.get(6).addLine(new Line(comRects.get(4).getPinRightDown().x2, comRects.get(4).getPinRightDown().y2,
                comRects.get(11).getPinLeftUp().x2, comRects.get(11).getPinLeftUp().y2));
        connections.get(6).addLine(new Line(comRects.get(11).getPinRightUp().x2, comRects.get(11).getPinRightUp().y2,
                labels.get(13).getPin().x2, labels.get(13).getPin().y2));
        connections.get(0).setRoad(new int[]{0, 1, 1});

        // ***************************************   CONNECTION 7   *************************************************

        connections.get(7).addLine(new Line(labels.get(7).getPin().x2, labels.get(7).getPin().y2,
                comRects.get(9).getPinLeftDown().x2, comRects.get(9).getPinLeftDown().y2));
        connections.get(7).addLine(new Line(comRects.get(9).getPinRightDown().x2, comRects.get(9).getPinRightDown().y2,
                comRects.get(10).getPinLeftDown().x2, comRects.get(10).getPinLeftDown().y2));
        connections.get(7).addLine(new Line(comRects.get(10).getPinRightDown().x2, comRects.get(10).getPinRightDown().y2,
                comRects.get(11).getPinLeftDown().x2, comRects.get(11).getPinLeftDown().y2));
        connections.get(7).addLine(new Line(comRects.get(11).getPinRightDown().x2, comRects.get(11).getPinRightDown().y2,
                labels.get(15).getPin().x2, labels.get(15).getPin().y2));
        connections.get(0).setRoad(new int[]{1, 1, 1});
    }

    @Override
    protected void drawConnections(Graphics2D g) {
        for (Connection i : connections) {
            i.draw(g);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                path.get(i).set(j, -1);
            }
            if (labels.get(i).getComponent().getText() != null && !labels.get(i).getComponent().getText().isEmpty()) {
                for (int j = 0; j < labels.get(i).getComponent().getText().length(); j++) {
                    path.get(i).set(j, (labels.get(i).getComponent().getText().charAt(j) - '0'));
                }
            }
        }

        // ***************************************   CONNECTION 0   *************************************************

        if (path.get(0).get(0) != -1) { // first digit exists
            setLineColor(0, 0, 0, 0);
            if (path.get(0).get(0) == 0) { // 0
                setLineColor(0, 1, 0, 0);
                setLineColor(1, 1, 8, 0);
                if (path.get(0).get(1) != -1) { // second digit exists
                    if (path.get(0).get(1) == 0) { // 00
                        setLineColor(0, 2, 0, 0);
                        setLineColor(2, 2, 8, 0);
                        if (path.get(0).get(2) != -1) { // third digit exists
                            if (path.get(0).get(2) == 0) { // 000
                                setLineColor(0, 3, 0, 0);
                                setLineColor(1, 3, 8, 0);
                            } else { // 001
                                setLineColor(1, 3, 0, 0);
                                setLineColor(0, 3, 8, 0);
                            }
                        } else {
                            setLineColor(0, 3, 8, 0);
                            setLineColor(1, 3, 8, 0);
                        }
                    } else { // 01
                        setLineColor(2, 2, 0, 0);
                        setLineColor(0, 2, 8, 0);
                        if (path.get(0).get(2) != -1) { // third digit exists
                            if (path.get(0).get(2) == 0) { // 010
                                setLineColor(2, 3, 0, 0);
                                setLineColor(3, 3, 8, 0);
                            } else { // 011
                                setLineColor(3, 3, 0, 0);
                                setLineColor(2, 3, 8, 0);
                            }
                        } else {
                            setLineColor(2, 3, 8, 0);
                            setLineColor(3, 3, 8, 0);
                        }
                    }
                } else {
                    setLineColor(0, 2, 8, 0);
                    setLineColor(2, 2, 8, 0);
                }
            } else { // 1
                setLineColor(4, 1, 0, 0);
                setLineColor(0, 1, 8, 0);
                if (path.get(0).get(1) != -1) { // second digit exists
                    if (path.get(0).get(1) == 0) { // 10
                        setLineColor(4, 2, 0, 0);
                        setLineColor(6, 2, 8, 0);
                        if (path.get(0).get(2) != -1) { // third digit exists
                            if (path.get(0).get(2) == 0) { // 100
                                setLineColor(4, 3, 0, 0);
                                setLineColor(5, 3, 8, 0);
                            } else { // 101
                                setLineColor(5, 3, 0, 0);
                                setLineColor(4, 3, 8, 0);
                            }
                        } else {
                            setLineColor(4, 3, 8, 0);
                            setLineColor(5, 3, 8, 0);
                        }
                    } else { // 11
                        setLineColor(6, 2, 0, 0);
                        setLineColor(4, 2, 8, 0);
                        if (path.get(0).get(2) != -1) { // third digit exists
                            if (path.get(0).get(2) == 0) { // 110
                                setLineColor(6, 3, 0, 0);
                                setLineColor(7, 3, 8, 0);
                            } else { // 111
                                setLineColor(7, 3, 0, 0);
                                setLineColor(6, 3, 8, 0);
                            }
                        } else {
                            setLineColor(6, 3, 8, 0);
                            setLineColor(7, 3, 8, 0);
                        }
                    }
                } else {
                    setLineColor(4, 2, 8, 0);
                    setLineColor(6, 2, 8, 0);
                }
            }
        } else {
            setLineColor(0, 0, 8, 0);
            setLineColor(0, 1, 8, 0);
            setLineColor(4, 1, 8, 0);
        }

        // ***************************************   CONNECTION 1   *************************************************

        if (path.get(1).get(0) != -1) { // first digit exists
            setLineColor(1, 0, 1, 1);
            if (path.get(1).get(0) == 0) { // 0
                setLineColor(1, 1, 1, 1);
                setLineColor(5, 1, 8, 1);
                if (path.get(1).get(1) != -1) { // second digit exists
                    if (path.get(1).get(1) == 0) { // 00
                        setLineColor(1, 2, 1, 1);
                        setLineColor(3, 2, 8, 1);
                        if (path.get(1).get(2) != -1) { // third digit exists
                            if (path.get(1).get(2) == 0) { // 000
                                setLineColor(0, 3, 1, 1);
                                setLineColor(1, 3, 8, 1);
                            } else { // 001
                                setLineColor(1, 3, 1, 1);
                                setLineColor(0, 3, 8, 1);
                            }
                        } else {
                            setLineColor(0, 3, 8, 1);
                            setLineColor(1, 3, 8, 1);
                        }
                    } else { // 01
                        setLineColor(3, 2, 1, 1);
                        setLineColor(1, 2, 8, 1);
                        if (path.get(1).get(2) != -1) { // third digit exists
                            if (path.get(1).get(2) == 0) { // 010
                                setLineColor(2, 3, 1, 1);
                                setLineColor(3, 3, 8, 1);
                            } else { // 011
                                setLineColor(3, 3, 1, 1);
                                setLineColor(2, 3, 8, 1);
                            }
                        } else {
                            setLineColor(2, 3, 8, 1);
                            setLineColor(3, 3, 8, 1);
                        }
                    }
                } else {
                    setLineColor(1, 2, 8, 1);
                    setLineColor(3, 2, 8, 1);
                }
            } else { // 1
                setLineColor(5, 1, 1, 1);
                setLineColor(1, 1, 8, 1);
                if (path.get(1).get(1) != -1) { // second digit exists
                    if (path.get(1).get(1) == 0) { // 10
                        setLineColor(5, 2, 1, 1);
                        setLineColor(7, 2, 8, 1);
                        if (path.get(1).get(2) != -1) { // third digit exists
                            if (path.get(1).get(2) == 0) { // 100
                                setLineColor(4, 3, 1, 1);
                                setLineColor(5, 3, 8, 1);
                            } else { // 101
                                setLineColor(5, 3, 1, 1);
                                setLineColor(4, 3, 8, 1);
                            }
                        } else {
                            setLineColor(4, 3, 8, 1);
                            setLineColor(5, 3, 8, 1);
                        }
                    } else { // 11
                        setLineColor(7, 2, 1, 1);
                        setLineColor(5, 2, 8, 1);
                        if (path.get(1).get(2) != -1) { // third digit exists
                            if (path.get(1).get(2) == 0) { // 110
                                setLineColor(6, 3, 1, 1);
                                setLineColor(7, 3, 8, 1);
                            } else { // 111
                                setLineColor(7, 3, 1, 1);
                                setLineColor(6, 3, 8, 1);
                            }
                        } else {
                            setLineColor(6, 3, 8, 1);
                            setLineColor(7, 3, 8, 1);
                        }
                    }
                } else {
                    setLineColor(5, 2, 8, 1);
                    setLineColor(7, 2, 8, 1);
                }
            }
        } else {
            setLineColor(1, 0, 8, 1);
            setLineColor(1, 1, 8, 1);
            setLineColor(5, 1, 8, 1);
        }

        // ***************************************   CONNECTION 2   *************************************************

        if (path.get(2).get(0) != -1) { // first digit exists
            setLineColor(2, 0, 2, 2);
            if (path.get(2).get(0) == 0) { // 0
                setLineColor(2, 1, 2, 2);
                setLineColor(6, 1, 8, 2);
                if (path.get(2).get(1) != -1) { // second digit exists
                    if (path.get(2).get(1) == 0) { // 00
                        setLineColor(0, 2, 2, 2);
                        setLineColor(2, 2, 8, 2);
                        if (path.get(2).get(2) != -1) { // third digit exists
                            if (path.get(2).get(2) == 0) { // 000
                                setLineColor(0, 3, 2, 2);
                                setLineColor(1, 3, 8, 2);
                            } else { // 001
                                setLineColor(1, 3, 2, 2);
                                setLineColor(0, 3, 8, 2);
                            }
                        } else {
                            setLineColor(0, 3, 8, 2);
                            setLineColor(1, 3, 8, 2);
                        }
                    } else { // 01
                        setLineColor(2, 2, 2, 2);
                        setLineColor(0, 2, 8, 2);
                        if (path.get(2).get(2) != -1) { // third digit exists
                            if (path.get(2).get(2) == 0) { // 010
                                setLineColor(2, 3, 2, 2);
                                setLineColor(3, 3, 8, 2);
                            } else { // 011
                                setLineColor(3, 3, 2, 2);
                                setLineColor(2, 3, 8, 2);
                            }
                        } else {
                            setLineColor(2, 3, 8, 2);
                            setLineColor(3, 3, 8, 2);
                        }
                    }
                } else {
                    setLineColor(0, 2, 8, 2);
                    setLineColor(2, 2, 8, 2);
                }
            } else { // 1
                setLineColor(6, 1, 2, 2);
                setLineColor(2, 1, 8, 2);
                if (path.get(2).get(1) != -1) { // second digit exists
                    if (path.get(2).get(1) == 0) { // 10
                        setLineColor(4, 2, 2, 2);
                        setLineColor(6, 2, 8, 2);
                        if (path.get(2).get(2) != -1) { // third digit exists
                            if (path.get(2).get(2) == 0) { // 100
                                setLineColor(4, 3, 2, 2);
                                setLineColor(5, 3, 8, 2);
                            } else { // 101
                                setLineColor(5, 3, 2, 2);
                                setLineColor(4, 3, 8, 2);
                            }
                        } else {
                            setLineColor(4, 3, 8, 2);
                            setLineColor(5, 3, 8, 2);
                        }
                    } else { // 11
                        setLineColor(6, 2, 2, 2);
                        setLineColor(4, 2, 8, 2);
                        if (path.get(2).get(2) != -1) { // third digit exists
                            if (path.get(2).get(2) == 0) { // 110
                                setLineColor(6, 3, 2, 2);
                                setLineColor(7, 3, 8, 2);
                            } else { // 111
                                setLineColor(7, 3, 2, 2);
                                setLineColor(6, 3, 8, 2);
                            }
                        } else {
                            setLineColor(6, 3, 8, 2);
                            setLineColor(7, 3, 8, 2);
                        }
                    }
                } else {
                    setLineColor(4, 2, 8, 2);
                    setLineColor(6, 2, 8, 2);
                }
            }
        } else {
            setLineColor(2, 0, 8, 2);
            setLineColor(2, 1, 8, 2);
            setLineColor(6, 1, 8, 2);
        }

        // ***************************************   CONNECTION 3   *************************************************

        if (path.get(3).get(0) != -1) { // first digit exists
            setLineColor(3, 0, 3, 3);
            if (path.get(3).get(0) == 0) { // 0
                setLineColor(3, 1, 3, 3);
                setLineColor(7, 1, 8, 3);
                if (path.get(3).get(1) != -1) { // second digit exists
                    if (path.get(3).get(1) == 0) { // 00
                        setLineColor(1, 2, 3, 3);
                        setLineColor(3, 2, 8, 3);
                        if (path.get(3).get(2) != -1) { // third digit exists
                            if (path.get(3).get(2) == 0) { // 000
                                setLineColor(0, 3, 3, 3);
                                setLineColor(1, 3, 8, 3);
                            } else { // 001
                                setLineColor(1, 3, 3, 3);
                                setLineColor(0, 3, 8, 3);
                            }
                        } else {
                            setLineColor(0, 3, 8, 3);
                            setLineColor(1, 3, 8, 3);
                        }
                    } else { // 01
                        setLineColor(3, 2, 3, 3);
                        setLineColor(1, 2, 8, 3);
                        if (path.get(3).get(2) != -1) { // third digit exists
                            if (path.get(3).get(2) == 0) { // 010
                                setLineColor(2, 3, 3, 3);
                                setLineColor(3, 3, 8, 3);
                            } else { // 011
                                setLineColor(3, 3, 3, 3);
                                setLineColor(2, 3, 8, 3);
                            }
                        } else {
                            setLineColor(2, 3, 8, 3);
                            setLineColor(3, 3, 8, 3);
                        }
                    }
                } else {
                    setLineColor(1, 2, 8, 3);
                    setLineColor(3, 2, 8, 3);
                }
            } else { // 1
                setLineColor(7, 1, 3, 3);
                setLineColor(3, 1, 8, 3);
                if (path.get(3).get(1) != -1) { // second digit exists
                    if (path.get(3).get(1) == 0) { // 10
                        setLineColor(5, 2, 3, 3);
                        setLineColor(7, 2, 8, 3);
                        if (path.get(3).get(2) != -1) { // third digit exists
                            if (path.get(3).get(2) == 0) { // 100
                                setLineColor(4, 3, 3, 3);
                                setLineColor(5, 3, 8, 3);
                            } else { // 101
                                setLineColor(5, 3, 3, 3);
                                setLineColor(4, 3, 8, 3);
                            }
                        } else {
                            setLineColor(4, 3, 8, 3);
                            setLineColor(5, 3, 8, 3);
                        }
                    } else { // 11
                        setLineColor(7, 2, 3, 3);
                        setLineColor(5, 2, 8, 3);
                        if (path.get(3).get(2) != -1) { // third digit exists
                            if (path.get(3).get(2) == 0) { // 110
                                setLineColor(6, 3, 3, 3);
                                setLineColor(7, 3, 8, 3);
                            } else { // 111
                                setLineColor(7, 3, 3, 3);
                                setLineColor(6, 3, 8, 3);
                            }
                        } else {
                            setLineColor(6, 3, 8, 3);
                            setLineColor(7, 3, 8, 3);
                        }
                    }
                } else {
                    setLineColor(5, 2, 8, 3);
                    setLineColor(7, 2, 8, 3);
                }
            }
        } else {
            setLineColor(3, 0, 8, 3);
            setLineColor(3, 1, 8, 3);
            setLineColor(7, 1, 8, 3);
        }

        // ***************************************   CONNECTION 4   *************************************************

        if (path.get(4).get(0) != -1) { // first digit exists
            setLineColor(4, 0, 4, 4);
            if (path.get(4).get(0) == 0) { // 0
                setLineColor(0, 1, 4, 4);
                setLineColor(4, 1, 8, 4);
                if (path.get(4).get(1) != -1) { // second digit exists
                    if (path.get(4).get(1) == 0) { // 00
                        setLineColor(0, 2, 4, 4);
                        setLineColor(2, 2, 8, 4);
                        if (path.get(4).get(2) != -1) { // third digit exists
                            if (path.get(4).get(2) == 0) { // 000
                                setLineColor(0, 3, 4, 4);
                                setLineColor(1, 3, 8, 4);
                            } else { // 001
                                setLineColor(1, 3, 4, 4);
                                setLineColor(0, 3, 8, 4);
                            }
                        } else {
                            setLineColor(0, 3, 8, 4);
                            setLineColor(1, 3, 8, 4);
                        }
                    } else { // 01
                        setLineColor(2, 2, 4, 4);
                        setLineColor(0, 2, 8, 4);
                        if (path.get(4).get(2) != -1) { // third digit exists
                            if (path.get(4).get(2) == 0) { // 010
                                setLineColor(2, 3, 4, 4);
                                setLineColor(3, 3, 8, 4);
                            } else { // 011
                                setLineColor(3, 3, 4, 4);
                                setLineColor(2, 3, 8, 4);
                            }
                        } else {
                            setLineColor(2, 3, 8, 4);
                            setLineColor(3, 3, 8, 4);
                        }
                    }
                } else {
                    setLineColor(0, 2, 8, 4);
                    setLineColor(2, 2, 8, 4);
                }
            } else { // 1
                setLineColor(4, 1, 4, 4);
                setLineColor(0, 1, 8, 4);
                if (path.get(4).get(1) != -1) { // second digit exists
                    if (path.get(4).get(1) == 0) { // 10
                        setLineColor(4, 2, 4, 4);
                        setLineColor(6, 2, 8, 4);
                        if (path.get(4).get(2) != -1) { // third digit exists
                            if (path.get(4).get(2) == 0) { // 100
                                setLineColor(4, 3, 4, 4);
                                setLineColor(5, 3, 8, 4);
                            } else { // 101
                                setLineColor(5, 3, 4, 4);
                                setLineColor(4, 3, 8, 4);
                            }
                        } else {
                            setLineColor(4, 3, 8, 4);
                            setLineColor(5, 3, 8, 4);
                        }
                    } else { // 11
                        setLineColor(6, 2, 4, 4);
                        setLineColor(4, 2, 8, 4);
                        if (path.get(4).get(2) != -1) { // third digit exists
                            if (path.get(4).get(2) == 0) { // 110
                                setLineColor(6, 3, 4, 4);
                                setLineColor(7, 3, 8, 4);
                            } else { // 111
                                setLineColor(7, 3, 4, 4);
                                setLineColor(6, 3, 8, 4);
                            }
                        } else {
                            setLineColor(6, 3, 8, 4);
                            setLineColor(7, 3, 8, 4);
                        }
                    }
                } else {
                    setLineColor(4, 2, 8, 4);
                    setLineColor(6, 2, 8, 4);
                }
            }
        } else {
            setLineColor(4, 0, 8, 4);
            setLineColor(0, 1, 8, 4);
            setLineColor(4, 1, 8, 4);
        }

        // ***************************************   CONNECTION 5   *************************************************

        if (path.get(5).get(0) != -1) { // first digit exists
            setLineColor(5, 0, 5, 5);
            if (path.get(5).get(0) == 0) { // 0
                setLineColor(1, 1, 5, 5);
                setLineColor(5, 1, 8, 5);
                if (path.get(5).get(1) != -1) { // second digit exists
                    if (path.get(5).get(1) == 0) { // 00
                        setLineColor(1, 2, 5, 5);
                        setLineColor(3, 2, 8, 5);
                        if (path.get(5).get(2) != -1) { // third digit exists
                            if (path.get(5).get(2) == 0) { // 000
                                setLineColor(0, 3, 5, 5);
                                setLineColor(1, 3, 8, 5);
                            } else { // 001
                                setLineColor(1, 3, 5, 5);
                                setLineColor(0, 3, 8, 5);
                            }
                        } else {
                            setLineColor(0, 3, 8, 5);
                            setLineColor(1, 3, 8, 5);
                        }
                    } else { // 01
                        setLineColor(3, 2, 5, 5);
                        setLineColor(1, 2, 8, 5);
                        if (path.get(5).get(2) != -1) { // third digit exists
                            if (path.get(5).get(2) == 0) { // 010
                                setLineColor(2, 3, 5, 5);
                                setLineColor(3, 3, 8, 5);
                            } else { // 011
                                setLineColor(3, 3, 5, 5);
                                setLineColor(2, 3, 8, 5);
                            }
                        } else {
                            setLineColor(2, 3, 8, 5);
                            setLineColor(3, 3, 8, 5);
                        }
                    }
                } else {
                    setLineColor(1, 2, 8, 5);
                    setLineColor(3, 2, 8, 5);
                }
            } else { // 1
                setLineColor(5, 1, 5, 5);
                setLineColor(1, 1, 8, 5);
                if (path.get(5).get(1) != -1) { // second digit exists
                    if (path.get(5).get(1) == 0) { // 10
                        setLineColor(5, 2, 5, 5);
                        setLineColor(7, 2, 8, 5);
                        if (path.get(5).get(2) != -1) { // third digit exists
                            if (path.get(5).get(2) == 0) { // 100
                                setLineColor(4, 3, 5, 5);
                                setLineColor(5, 3, 8, 5);
                            } else { // 101
                                setLineColor(5, 3, 5, 5);
                                setLineColor(4, 3, 8, 5);
                            }
                        } else {
                            setLineColor(4, 3, 8, 5);
                            setLineColor(5, 3, 8, 5);
                        }
                    } else { // 11
                        setLineColor(7, 2, 5, 5);
                        setLineColor(5, 2, 8, 5);
                        if (path.get(5).get(2) != -1) { // third digit exists
                            if (path.get(5).get(2) == 0) { // 110
                                setLineColor(6, 3, 5, 5);
                                setLineColor(7, 3, 8, 5);
                            } else { // 111
                                setLineColor(7, 3, 5, 5);
                                setLineColor(6, 3, 8, 5);
                            }
                        } else {
                            setLineColor(6, 3, 8, 5);
                            setLineColor(7, 3, 8, 5);
                        }
                    }
                } else {
                    setLineColor(5, 2, 8, 5);
                    setLineColor(7, 2, 8, 5);
                }
            }
        } else {
            setLineColor(5, 0, 8, 5);
            setLineColor(1, 1, 8, 5);
            setLineColor(5, 1, 8, 5);
        }

        // ***************************************   CONNECTION 6   *************************************************

        if (path.get(6).get(0) != -1) { // first digit exists
            setLineColor(6, 0, 6, 6);
            if (path.get(6).get(0) == 0) { // 0
                setLineColor(2, 1, 6, 6);
                setLineColor(6, 1, 8, 6);
                if (path.get(6).get(1) != -1) { // second digit exists
                    if (path.get(6).get(1) == 0) { // 00
                        setLineColor(0, 2, 6, 6);
                        setLineColor(2, 2, 8, 6);
                        if (path.get(6).get(2) != -1) { // third digit exists
                            if (path.get(6).get(2) == 0) { // 000
                                setLineColor(0, 3, 6, 6);
                                setLineColor(1, 3, 8, 6);
                            } else { // 001
                                setLineColor(1, 3, 6, 6);
                                setLineColor(0, 3, 8, 6);
                            }
                        } else {
                            setLineColor(0, 3, 8, 6);
                            setLineColor(1, 3, 8, 6);
                        }
                    } else { // 01
                        setLineColor(2, 2, 6, 6);
                        setLineColor(0, 2, 8, 6);
                        if (path.get(6).get(2) != -1) { // third digit exists
                            if (path.get(6).get(2) == 0) { // 010
                                setLineColor(2, 3, 6, 6);
                                setLineColor(3, 3, 8, 6);
                            } else { // 011
                                setLineColor(3, 3, 6, 6);
                                setLineColor(2, 3, 8, 6);
                            }
                        } else {
                            setLineColor(2, 3, 8, 6);
                            setLineColor(3, 3, 8, 6);
                        }
                    }
                } else {
                    setLineColor(0, 2, 8, 6);
                    setLineColor(2, 2, 8, 6);
                }
            } else { // 1
                setLineColor(6, 1, 6, 6);
                setLineColor(2, 1, 8, 6);
                if (path.get(6).get(1) != -1) { // second digit exists
                    if (path.get(6).get(1) == 0) { // 10
                        setLineColor(4, 2, 6, 6);
                        setLineColor(6, 2, 8, 6);
                        if (path.get(6).get(2) != -1) { // third digit exists
                            if (path.get(6).get(2) == 0) { // 100
                                setLineColor(4, 3, 6, 6);
                                setLineColor(5, 3, 8, 6);
                            } else { // 101
                                setLineColor(5, 3, 6, 6);
                                setLineColor(4, 3, 8, 6);
                            }
                        } else {
                            setLineColor(4, 3, 8, 6);
                            setLineColor(5, 3, 8, 6);
                        }
                    } else { // 11
                        setLineColor(6, 2, 6, 6);
                        setLineColor(4, 2, 8, 6);
                        if (path.get(6).get(2) != -1) { // third digit exists
                            if (path.get(6).get(2) == 0) { // 110
                                setLineColor(6, 3, 6, 6);
                                setLineColor(7, 3, 8, 6);
                            } else { // 111
                                setLineColor(7, 3, 6, 6);
                                setLineColor(6, 3, 8, 6);
                            }
                        } else {
                            setLineColor(6, 3, 8, 6);
                            setLineColor(7, 3, 8, 6);
                        }
                    }
                } else {
                    setLineColor(4, 2, 8, 6);
                    setLineColor(6, 2, 8, 6);
                }
            }
        } else {
            setLineColor(6, 0, 8, 6);
            setLineColor(2, 1, 8, 6);
            setLineColor(6, 1, 8, 6);
        }

        // ***************************************   CONNECTION 7   *************************************************

        if (path.get(7).get(0) != -1) { // first digit exists
            setLineColor(7, 0, 7, 7);
            if (path.get(7).get(0) == 0) { // 0
                setLineColor(3, 1, 7, 7);
                setLineColor(7, 1, 8, 7);
                if (path.get(7).get(1) != -1) { // second digit exists
                    if (path.get(7).get(1) == 0) { // 00
                        setLineColor(1, 2, 7, 7);
                        setLineColor(3, 2, 8, 7);
                        if (path.get(7).get(2) != -1) { // third digit exists
                            if (path.get(7).get(2) == 0) { // 000
                                setLineColor(0, 3, 7, 7);
                                setLineColor(1, 3, 8, 7);
                            } else { // 001
                                setLineColor(1, 3, 7, 7);
                                setLineColor(0, 3, 8, 7);
                            }
                        } else {
                            setLineColor(0, 3, 8, 7);
                            setLineColor(1, 3, 8, 7);
                        }
                    } else { // 01
                        setLineColor(3, 2, 7, 7);
                        setLineColor(1, 2, 8, 7);
                        if (path.get(7).get(2) != -1) { // third digit exists
                            if (path.get(7).get(2) == 0) { // 010
                                setLineColor(2, 3, 7, 7);
                                setLineColor(3, 3, 8, 7);
                            } else { // 011
                                setLineColor(3, 3, 7, 7);
                                setLineColor(2, 3, 8, 7);
                            }
                        } else {
                            setLineColor(2, 3, 8, 7);
                            setLineColor(3, 3, 8, 7);
                        }
                    }
                } else {
                    setLineColor(1, 2, 8, 7);
                    setLineColor(3, 2, 8, 7);
                }
            } else { // 1
                setLineColor(7, 1, 7, 7);
                setLineColor(3, 1, 8, 7);
                if (path.get(7).get(1) != -1) { // second digit exists
                    if (path.get(7).get(1) == 0) { // 10
                        setLineColor(5, 2, 7, 7);
                        setLineColor(7, 2, 8, 7);
                        if (path.get(7).get(2) != -1) { // third digit exists
                            if (path.get(7).get(2) == 0) { // 100
                                setLineColor(4, 3, 7, 7);
                                setLineColor(5, 3, 8, 7);
                            } else { // 101
                                setLineColor(5, 3, 7, 7);
                                setLineColor(4, 3, 8, 7);
                            }
                        } else {
                            setLineColor(4, 3, 8, 7);
                            setLineColor(5, 3, 8, 7);
                        }
                    } else { // 11
                        setLineColor(7, 2, 7, 7);
                        setLineColor(5, 2, 8, 7);
                        if (path.get(7).get(2) != -1) { // third digit exists
                            if (path.get(7).get(2) == 0) { // 110
                                setLineColor(6, 3, 7, 7);
                                setLineColor(7, 3, 8, 7);
                            } else { // 111
                                setLineColor(7, 3, 7, 7);
                                setLineColor(6, 3, 8, 7);
                            }
                        } else {
                            setLineColor(6, 3, 8, 7);
                            setLineColor(7, 3, 8, 7);
                        }
                    }
                } else {
                    setLineColor(5, 2, 8, 7);
                    setLineColor(7, 2, 8, 7);
                }
            }
        } else {
            setLineColor(7, 0, 8, 7);
            setLineColor(3, 1, 8, 7);
            setLineColor(7, 1, 8, 7);
        }
    }

    @Override
    protected void drawLabelValue(Graphics2D g) {
        labels.get(8).drawValue(g, "000");
        labels.get(9).drawValue(g, "100");
        labels.get(10).drawValue(g, "001");
        labels.get(11).drawValue(g, "101");
        labels.get(12).drawValue(g, "010");
        labels.get(13).drawValue(g, "110");
        labels.get(14).drawValue(g, "011");
        labels.get(15).drawValue(g, "111");
    }
}
