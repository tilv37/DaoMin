import { TestBed } from '@angular/core/testing';

import { PostAdminService } from './post-admin.service';

describe('PostAdminService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PostAdminService = TestBed.get(PostAdminService);
    expect(service).toBeTruthy();
  });
});
