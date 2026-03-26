package view;
Button btn = new Button(texto);
            btn.setPrefSize(60, 60);

            btn.setOnAction(e -> {
        try {
        if (texto.equals("C")) {
        display.clear();
                    } else if (texto.equals("=")) {
String expressao = display.getText();
double resultado = calcular(expressao);
                        historico.add(expressao + " = " + resultado);
                        areaHistorico.setText(String.join("\n", historico));
        display.setText(String.valueOf(resultado));
        } else {
        display.appendText(texto);
                    }
                            } catch (Exception ex) {
mostrarErro("Erro no cálculo!");
                }
                        });

                        grid.add(btn, coluna, linha);

coluna++;
        if (coluna == 4) {
coluna = 0;
linha++;
        }
        }

VBox layout = new VBox(10, display, grid, new Label("Histórico"), areaHistorico);
        layout.setPadding(new Insets(15));

Scene scene = new Scene(layout, 300, 450);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Calculadora Completa");
        stage.show();
    }

private double calcular(String exp) {
    if (exp.contains("+")) {
        String[] p = exp.split("\\+");
        return service.somar(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    } else if (exp.contains("-")) {
        String[] p = exp.split("-");
        return service.subtrair(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    } else if (exp.contains("*")) {
        String[] p = exp.split("\\*");
        return service.multiplicar(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    } else if (exp.contains("/")) {
        String[] p = exp.split("/");
        return service.dividir(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    }
    throw new IllegalArgumentException("Expressão inválida");
}

private void mostrarErro(String msg) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erro");
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}
}
