    package com.conversordivisas.conversormoneda.model;


        import java.util.HashMap;
        import java.util.Map;

        public class ConversionLong {

            private double value;
            private String sourceUnit;
            private String targetUnit;
            private double result;

            private static final Map<String, Double> CONVERSION_FACTORS = new HashMap<>();
            static {
                CONVERSION_FACTORS.put("millimeter", 0.001);
                CONVERSION_FACTORS.put("centimeter", 0.01);
                CONVERSION_FACTORS.put("meter", 1.0);
                CONVERSION_FACTORS.put("kilometer", 1000.0);
                CONVERSION_FACTORS.put("inch", 0.0254);
                CONVERSION_FACTORS.put("foot", 0.3048);
                CONVERSION_FACTORS.put("yard", 0.9144);
                CONVERSION_FACTORS.put("mile", 1609.344);
                CONVERSION_FACTORS.put("nauticalMile", 1852.0);
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }

            public String getSourceUnit() {
                return sourceUnit;
            }

            public void setSourceUnit(String sourceUnit) {
                this.sourceUnit = sourceUnit;
            }

            public String getTargetUnit() {
                return targetUnit;
            }

            public void setTargetUnit(String targetUnit) {
                this.targetUnit = targetUnit;
            }

            public double getResult() {
                return result;
            }

            public void setResult(double result) {
                this.result = result;
            }

            public String getTargetUnitName() {
                switch (targetUnit) {
                    case "millimeter":
                        return "Milímetros";
                    case "centimeter":
                        return "Centímetros";
                    case "meter":
                        return "Metros";
                    case "kilometer":
                        return "Kilómetros";
                    case "inch":
                        return "Pulgadas";
                    case "foot":
                        return "Pies";
                    case "yard":
                        return "Yardas";
                    case "mile":
                        return "Millas";
                    case "nauticalMile":
                        return "Millas Náuticas";
                    default:
                        return "";
                }
            }

            public void convert() {
                double factor = CONVERSION_FACTORS.get(sourceUnit) / CONVERSION_FACTORS.get(targetUnit);
                result = value * factor;
            }
        }