import { TestBed } from '@angular/core/testing';

import { AuthentiticationService } from './authentication.service';

describe('AuthentiticationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthentiticationService = TestBed.get(AuthentiticationService);
    expect(service).toBeTruthy();
  });
});
