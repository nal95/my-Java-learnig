import { TestBed } from '@angular/core/testing';

import { ClinicalHttpService } from './clinical-http.service';

describe('ClinicalHttpService', () => {
  let service: ClinicalHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClinicalHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
