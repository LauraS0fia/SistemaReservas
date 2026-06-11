export const AVAILABLE_SERVICES = [
	'Corte de cabello',
	'Manicura y pedicura',
	'Masaje terapéutico',
	'Limpieza dental',
	'Entrenamiento personal',
] as const;

export type AvailableService = (typeof AVAILABLE_SERVICES)[number];
